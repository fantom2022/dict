package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanTaskDto implements Serializable {
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
