package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DICT_CODE_DIRECTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDirection {
    @Id
    private Long id;
    private Long code;
    private String name;
    private String shortName;
    private Long typeCode;
    private Long childId;
    private String path;
}