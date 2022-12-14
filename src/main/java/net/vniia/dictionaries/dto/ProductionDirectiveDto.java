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
public class ProductionDirectiveDto extends AuditableDto implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String shortName;
    private Boolean isArchived;
    private String codeWithName;
}
