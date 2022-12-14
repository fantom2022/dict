package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDto implements Serializable {
    private String header;
    private List<DictionaryColumnDto> columns;

    public DictionaryDto(List<DictionaryColumnDto> columns) {
        this.columns = columns;
    }
}
