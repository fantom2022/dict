package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DICT_CODE_COMPOSITION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeComposition {
    @Id
    private Long parentId;
    private Long childrenId;
}