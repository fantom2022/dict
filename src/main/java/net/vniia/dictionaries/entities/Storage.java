package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.entities.enums.StorehouseTypeEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DICT_STORAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    @Id
    private Long id;
    private String name;
    private String shortName;
    private Boolean isArchived;
}
