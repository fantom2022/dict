package net.vniia.dictionaries.integration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("dictionaries")
@Data
public class DictionaryConfigProps {
    Map<String, Boolean> kafka;
}
