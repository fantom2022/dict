package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_MATERIAL_RESOURCE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResource {
    @Id
    private Long id;
    private Long idInner;
    private Long idRef;
    private String designation;
    private String name;
    private Date updateDate;

    public MaterialResource(Long idInner, String designation, String name, Date updateDate) {
        this.idInner = idInner;
        this.designation = designation;
        this.name = name;
        this.updateDate = updateDate;
    }
}
