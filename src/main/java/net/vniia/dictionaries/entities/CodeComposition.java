package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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