package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.common.audit.AuditableDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkshopGroupWorkshopDto extends AuditableDto implements Serializable {
    private Long id;
    private WorkshopDto workshop;
    private WorkshopGroupDto workshopGroup;
    private Boolean archive;
}
