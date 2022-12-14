package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryColumnDto implements Serializable {
    private String field;
    private String header;
    private String type;

    public DictionaryColumnDto(String field, String header) {
        this.field = field;
        this.header = header;
    }
}
