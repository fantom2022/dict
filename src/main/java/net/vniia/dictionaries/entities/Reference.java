package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
