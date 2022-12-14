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
public class CodeDirectionDto implements Serializable {
    private Long id;
    private Long code;
    private String name;
    private String shortName;
    private Long typeCode;
    private Long childId;
    private String path;
}
