package net.vniia.dictionaries.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.utils.TimestampToZonedDateTimeConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DICT_CUSTOM_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomOrder {
    @Id
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
    @JsonDeserialize(converter = TimestampToZonedDateTimeConverter.class)
    private ZonedDateTime updateDate;
    private boolean archive;
}
