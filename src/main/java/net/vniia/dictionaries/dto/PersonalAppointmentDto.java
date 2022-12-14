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
public class PersonalAppointmentDto implements Serializable {
    private Long id;
    private String personalNumber;
    private PersonDto person;
    private Date beginDate;
    private Date dismissedDate;
    private DepartmentDto department;
    private PositionDto position;
    private String fullName;
    private String shortName;
}
