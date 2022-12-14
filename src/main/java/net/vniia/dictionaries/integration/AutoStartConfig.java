package net.vniia.dictionaries.integration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;

@Configuration
@EnableConfigurationProperties(DictionaryConfigProps.class)
@Log4j2
class AutoStartConfig {
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    private DictionaryConfigProps props;

    @EventListener(ApplicationReadyEvent.class)
    void onStartup() {
        if (props == null || props.kafka == null) {
            return;
        }
        props.kafka.keySet().stream()
                .filter(d -> props.kafka.get(d) != null && props.kafka.get(d))
                .map(d -> "dictionary" + d.substring(0, 1).toUpperCase() + d.substring(1))
                .forEach(n -> {
                    try {
                        MessageListenerContainer container = kafkaListenerEndpointRegistry
                                .getListenerContainer(n);
                        if (container != null) {
                            container.start();
                        }
                    } catch (Exception ex) {
                        log.warn(n, ex);
                    }
                });

        try {
            MessageListenerContainer container = kafkaListenerEndpointRegistry
                    .getListenerContainer("dictionaryResetListener");
            if (container != null) {
                container.start();
            }
        } catch (Exception ex) {
            log.warn("dictionaryResetListener", ex);
        }
    }
}
