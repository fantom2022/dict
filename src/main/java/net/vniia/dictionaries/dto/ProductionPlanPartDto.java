package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.common.audit.AuditableDto;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionPlanPartDto extends AuditableDto implements Serializable {
    private Long id;
    private Long part;
    private String partName;
    private String partWithPartName;
    private Boolean archive;
}
