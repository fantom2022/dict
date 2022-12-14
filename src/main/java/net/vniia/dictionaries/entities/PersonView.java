package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Subselect("select id, concat_ws(' ', last_name, first_name, middle_name) as full_name  from DICT_PERSON")
public class PersonView {
    @Id
    private Long id;
    private String fullName;
}
