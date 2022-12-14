package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentChiefDto {
    private PersonalDto personal;
    private Long departmentId;
    private boolean chief;
}
