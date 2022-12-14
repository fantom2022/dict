package net.vniia.dictionaries.autoconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"net.vniia.dictionaries.entities", "net.vniia.dictionaries.dto"})
@ComponentScan("net.vniia.dictionaries")
public class DictionariesAutoConfig {
}
