package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DICT_APPOINTMENT")
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @Column
    private Long id;

    @Column
    private Date beginDate;

    @Column
    private Date endDate;

    @Column
    private Long personalId;

    @Column
    private Long departmentId;

    @Column
    private Long positionId;

    @Column
    private Long placeId;

    @Column
    private Date updateDate;

    @Transient
    private boolean archive;
}
