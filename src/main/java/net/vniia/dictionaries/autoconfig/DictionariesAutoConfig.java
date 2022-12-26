package net.vniia.dictionaries.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.vniia.dictionaries.entities", "net.vniia.dictionaries.dto"})
@ComponentScan("net.vniia.dictionaries")
public class DictionariesAutoConfig {
}
