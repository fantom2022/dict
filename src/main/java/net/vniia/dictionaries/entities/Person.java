package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_PERSON")
@Data
@NoArgsConstructor
public class Person {
    @Id
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
}
