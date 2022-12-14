package net.vniia.dictionaries.integration;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.adapter.BatchMessagingMessageListenerAdapter;
import org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class ResetTopicListener {
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @KafkaListener(topics = "reset-topic", containerFactory = "dictionaryReset",
            id = "dictionaryResetListener", autoStartup = "false")
    @Transactional
    public void onReset(String topicName) {
        MessageListenerContainer container = kafkaListenerEndpointRegistry.getAllListenerContainers()
                .stream()
                .filter(k -> k.getAssignedPartitions().stream().anyMatch(p -> p.topic().equals(topicName)))
                .findAny()
                .orElse(null);

        TableDataListener listener = null;
        if (container != null && container.getContainerProperties() != null) {
            if (container.getContainerProperties().getMessageListener() instanceof TableDataListener )
                listener = (TableDataListener) container.getContainerProperties().getMessageListener();

            if (container.getContainerProperties().getMessageListener() instanceof MessagingMessageListenerAdapter) {
                BatchMessagingMessageListenerAdapter adapter =
                        (BatchMessagingMessageListenerAdapter) container.getContainerProperties().getMessageListener();
                try {
                    listener = (TableDataListener) FieldUtils.readField(adapter, "bean", true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (listener != null) {
                listener.resetTopicOffset(container.getContainerProperties().getTopics()[0]);
            }
        }
    }
}
