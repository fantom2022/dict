package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.common.audit.defaultImpl.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DICT_PRODUCTION_DIRECTIVE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionDirective extends Auditable {
    @Id
    private Long id;
    private String code;
    private String name;
    private String shortName;
    private Boolean isArchived;
}
