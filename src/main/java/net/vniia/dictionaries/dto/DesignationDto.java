package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignationDto implements Serializable {
    private Long id;
    private String designation;
    private String name;
    private LocalDate updateDate;
    private String updateUser;
}
