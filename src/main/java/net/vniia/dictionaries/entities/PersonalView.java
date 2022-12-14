package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Subselect( "select a.id, a.personal_number, b.last_name || ' ' || b.first_name || ' ' || b.middle_name || case when c.code is null then '' else ' [' || c.code || ']' end as full_name, " +
            "b.last_name || ' ' || substring(b.first_name, 1, 1) || '.' || ' ' || substring(b.middle_name, 1, 1) || '.' as short_name, " +
            "b.id as person_id," +
            "a.work_type as work_type " +
            "from DICT_PERSONAL a " +
            "INNER JOIN DICT_PERSON b on a.person_id = b.id " +
            "LEFT JOIN DICT_DEPARTMENT c on a.department_id = c.id")
public class PersonalView {
    @Id
    private Long id;
    private Long personId;
    private String fullName;
    private String personalNumber;
    private String shortName;
    private Long workType;
}
