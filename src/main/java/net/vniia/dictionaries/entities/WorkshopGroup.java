package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.common.audit.defaultImpl.Auditable;
import net.vniia.dictionaries.dto.WorkshopGroupDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "DICT_WORKSHOP_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopGroup extends Auditable {
    @Id
    private Long id;
    private String title;
    private Boolean archive;

    public WorkshopGroup(WorkshopGroupDto workshopGroup) {
        this.update(workshopGroup);
    }

    public void update(WorkshopGroupDto workshopGroup) {
        this.id = Optional.ofNullable(workshopGroup.getId()).orElse(null);
        this.title = Optional.ofNullable(workshopGroup.getTitle()).orElse(null);
        this.archive = Optional.ofNullable(workshopGroup.getArchive()).orElse(false);
    }
}