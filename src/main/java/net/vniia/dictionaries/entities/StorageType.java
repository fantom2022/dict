package net.vniia.dictionaries.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "DICT_STORAGE_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageType {
    @Id
    private Long id;
    private String name;
    private String shortName;
    private Boolean isArchived;
}
