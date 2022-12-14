package net.vniia.dictionaries.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WasteBusinessServiceConfig {
    @ConditionalOnMissingBean
    @Bean("wasteBusinessService")
    public WasteBusinessService getDummyWasteBusinessService() {
        return new DummyWasteBusinessService();
    }
}
