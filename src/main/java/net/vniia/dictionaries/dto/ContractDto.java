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
public class ContractDto implements Serializable {
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
