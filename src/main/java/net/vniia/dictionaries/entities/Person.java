package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
