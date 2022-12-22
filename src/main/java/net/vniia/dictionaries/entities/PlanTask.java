package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_PLAN_TASK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanTask {
    @Id
    private Long id;
    private String category;
    private Long parentId;
    private Long quantity;
    private Date launchDate;
    private Date releaseDate;
    private String note;
    private Date closingDate;
    private Boolean isClosed;
    private String customer;
    private Long priorityId;
    private Long priority;
    private Long plantaskTypeId;
    private String plantaskType;
    private Long positionNumber;
    private Long parentPositionNumber;
    private Long plantaskKindId;
    private String plantaskKind;
    private Long plantaskStatusId;
    private String plantaskStatus;
    private Long customOrderId;
    private Long customOrderNumber;
    private Long deliveryDepartmentId;
    private String deliveryDepartment;
    private String designation;
    private String litera;
    private String designationAnalog;
    private Date updateDate;
}
