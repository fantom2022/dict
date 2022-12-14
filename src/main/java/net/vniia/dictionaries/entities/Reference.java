package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "DICT_REFERENCE")
@NoArgsConstructor
@AllArgsConstructor
public class Reference {
    @Id
    private Long id;
    private String code;
    private String shortName;
    private String fullName;
}
