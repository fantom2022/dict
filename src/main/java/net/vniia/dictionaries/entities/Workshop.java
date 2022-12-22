package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DICT_WORKSHOP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workshop {
    @Id
    private Long id;
    private String code;
    private Long departmentId;
    private boolean isArchived;

    @ElementCollection
    @CollectionTable(name = "DICT_WORKSHOP_DEPARTMENT")
    @Column(name = "DEPARTMENT_ID")
    private Set<Long> departments;

    @Column(name = "parent_department_code")
    private String parentDepartmentCode;

}
