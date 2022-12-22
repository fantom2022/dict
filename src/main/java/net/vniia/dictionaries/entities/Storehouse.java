package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.dto.StorehouseDto;
import net.vniia.dictionaries.entities.enums.StorehouseTypeEnum;

import jakarta.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "DICT_STOREHOUSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storehouse {
    @Id
    private Long id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private StorehouseTypeEnum type;
    private Long departmentId;
    private Long placeId;
    private Boolean isArchived;
}
