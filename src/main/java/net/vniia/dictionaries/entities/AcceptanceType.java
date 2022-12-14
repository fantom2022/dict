package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.common.audit.defaultImpl.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "DICT_ACCEPTANCE_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptanceType extends Auditable {

    @Id
    private Long id;
    private String code;
    private String name;
    private Long importance;
    private Boolean isArchived;
}
