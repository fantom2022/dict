package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_CONTRACT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    private Long id;
    private Long parentId;
    private String registrationNumber;
    private String name;
    private String number;
    private Date date;
    private Long directionId;
    private String direction;
    private Long contractorId;
    private Long workKindId;
    private String workKind;
    private Long taxTypeId;
    private Long customOrderId;
    private Boolean isArchived;
    private Date updateDate;
}
