package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DICT_WASTE")
@Data
@NoArgsConstructor
public class Waste {
    @Id
    private Long id;
    private Long productId;
    private BigDecimal waste;
    private Long witnessCount;
    private Long destroyControlCount;
    private Long setUpEquipmentCount;
    private Long optimalProductionBatch;
    private Date updateDate;
}
