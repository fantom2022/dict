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
public class StorageTypeDto implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private Boolean isArchived;
}
