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
public class UnitMeasureDto implements Serializable {
    private Long id;
    private String fullName;
    private String name;
    private String nameLower;
    private String okeiCode;
    private Date updateDate;
}
