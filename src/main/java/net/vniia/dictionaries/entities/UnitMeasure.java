package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_UNIT_MEASURE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitMeasure {
    @Id
    private Long id;
    private String fullName;
    private String name;
    private String nameLower;
    private String okeiCode;
    private Date updateDate;
}
