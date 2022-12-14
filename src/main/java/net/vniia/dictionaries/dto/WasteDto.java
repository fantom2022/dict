package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteDto implements Serializable {
    private Long id;
    private Long productId;
    private BigDecimal waste;
    private Long witnessCount;
    private Long destroyControlCount;
    private Long setUpEquipmentCount;
    private Long optimalProductionBatch;
    private Date updateDate;
}
