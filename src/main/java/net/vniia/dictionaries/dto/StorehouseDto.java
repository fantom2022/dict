package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.entities.enums.StorehouseTypeEnum;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorehouseDto implements Serializable {
    private Long id;
    private String name;
    private String code;
    private DepartmentDto department;
    private PlaceDto place;
    private Boolean isArchived;
    private StorehouseTypeEnum type;
}
