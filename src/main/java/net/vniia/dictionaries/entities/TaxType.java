package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DICT_TAX_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxType {
    @Id
    private Long id;
    private String name;
    private BigDecimal factor;
    private Boolean isArchived;
    private Date updateDate;
}
