package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomOrderDto implements Serializable {
    private Long id;
    private String orderNumber;
    private String name;
    private int stateId;
    private String state;
    private String exState;
    private LocalDate openDate;
    private LocalDate closeDate;
    private LocalDate stopDate;
    private Long subjectId;
    private String subject;
    private Long landId;
    private String land;
    private Long workTypeId;
    private String workType;
    private Long productKindId;
    private String productKind;
    private String economistName;
    private String economistPosition;
    private String economistDepartment;
    private String themeLeadName;
    private String themeLeadPosition;
    private String themeLeadDepartment;
    private String expenseArticles;
    private boolean salary;
    private boolean material;
    private String departments;
    private ZonedDateTime updateDate;
    private boolean archive;
}
