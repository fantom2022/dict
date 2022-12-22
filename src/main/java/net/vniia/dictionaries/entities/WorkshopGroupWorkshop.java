package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.common.audit.defaultImpl.Auditable;
import net.vniia.dictionaries.dto.WorkshopDto;
import net.vniia.dictionaries.dto.WorkshopGroupDto;
import net.vniia.dictionaries.dto.WorkshopGroupWorkshopDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "DICT_WORKSHOP_GROUP_WORKSHOP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopGroupWorkshop extends Auditable {
    @Id
    private Long id;
    private Long workshopId;
    private Long workshopGroupId;
    private Boolean archive;

    public WorkshopGroupWorkshop(WorkshopGroupWorkshopDto workshopGroupWorkshop) {
        this.update(workshopGroupWorkshop);
    }

    public void update(WorkshopGroupWorkshopDto workshopGroupWorkshop) {
        this.id = Optional.ofNullable(workshopGroupWorkshop.getId()).orElse(null);
        this.workshopId = Optional.ofNullable(workshopGroupWorkshop.getWorkshop())
                .map(WorkshopDto::getId).orElse(null);
        this.workshopGroupId = Optional.ofNullable(workshopGroupWorkshop.getWorkshopGroup())
                .map(WorkshopGroupDto::getId).orElse(null);
        this.archive = Optional.ofNullable(workshopGroupWorkshop.getArchive()).orElse(null);
    }
}
