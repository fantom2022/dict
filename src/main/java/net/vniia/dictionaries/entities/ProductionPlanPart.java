package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.common.audit.defaultImpl.Auditable;
import net.vniia.dictionaries.dto.ProductionPlanPartDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "DICT_PRODUCTION_PLAN_PART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionPlanPart extends Auditable {

    @Id
    private Long id;
    private Long part;
    private String partName;
    private Boolean archive;

    public ProductionPlanPart(ProductionPlanPartDto planPart) {
        this.update(planPart);
    }

    public void update(ProductionPlanPartDto planPart) {
        this.part = Optional.ofNullable(planPart.getPart()).orElse(null);
        this.partName = Optional.ofNullable(planPart.getPartName()).orElse(null);
        this.archive = Optional.ofNullable(planPart.getArchive()).orElse(false);
    }
}
