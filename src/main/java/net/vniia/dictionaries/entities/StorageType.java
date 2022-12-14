package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
