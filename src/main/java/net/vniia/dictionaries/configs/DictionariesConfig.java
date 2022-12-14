package net.vniia.dictionaries.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"net.vniia.dictionaries.entities", "net.vniia.dictionaries.dto"})
public class DictionariesConfig {
}
