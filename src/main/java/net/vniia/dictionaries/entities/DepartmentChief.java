package net.vniia.dictionaries.entities;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DICT_DEPARTMENT_CHIEF")
@IdClass(DepartmentChiefId.class)
@Data
@NoArgsConstructor
public class DepartmentChief {
    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Id
    @Column(name = "PERSONAL_ID")
    private Long personalId;

    @Id
    @Column(name = "CHIEF")
    private boolean chief;
}

class DepartmentChiefId implements Serializable {
    private Long departmentId;
    private Long personalId;
    private boolean chief;
}



