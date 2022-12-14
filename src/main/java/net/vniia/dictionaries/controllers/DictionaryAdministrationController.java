package net.vniia.dictionaries.controllers;

import net.vniia.common.exception.NotFoundException;
import net.vniia.dictionaries.integration.DictionaryConfigProps;
import net.vniia.dictionaries.integration.TableDataListener;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.adapter.BatchMessagingMessageListenerAdapter;
import org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
@RequestMapping("administration")
public class DictionaryAdministrationController {

    @Autowired
    private DictionaryConfigProps props;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("dictionaries")
    private List<String> dictionaries() {
        if (props == null || props.getKafka() == null) {
            return Collections.emptyList();
        }
        return props.getKafka().keySet().stream()
                .filter(d -> props.getKafka().get(d) != null && props.getKafka().get(d))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("dictionaries/{name}/action/reload")
    public void reloadInboundTopicData(@PathVariable("name") String name) {
        String containerId = "dictionary" + name.substring(0, 1).toUpperCase() + name.substring(1);
        MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(containerId);

        TableDataListener listener = null;
        if (container != null && container.getContainerProperties() != null) {
            if (container.getContainerProperties().getMessageListener() instanceof TableDataListener )
                listener = (TableDataListener) container.getContainerProperties().getMessageListener();

            if (container.getContainerProperties().getMessageListener() instanceof MessagingMessageListenerAdapter) {
                BatchMessagingMessageListenerAdapter adapter = (BatchMessagingMessageListenerAdapter) container.getContainerProperties().getMessageListener();
                try {
                    listener = (TableDataListener) FieldUtils.readField(adapter, "bean", true);
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        if (listener == null) throw new NotFoundException("Dictionary " + name + " not found");
        listener.resetTopicOffset(container.getContainerProperties().getTopics()[0]);
    }
}
