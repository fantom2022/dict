package net.vniia.dictionaries.autoconfig;

import net.vniia.dictionaries.configs.FlywayMigrationWithDictionariesStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayMigrationAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(FlywayMigrationStrategy.class)
    FlywayMigrationStrategy flywayMigrationWithDictionariesStrategy() {
        return new FlywayMigrationWithDictionariesStrategy();
    }
}
