package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.common.audit.AuditableDto;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopGroupDto extends AuditableDto implements Serializable {
    private Long id;
    private String title;
    private Boolean archive;
}
