package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxTypeDto implements Serializable {
    private Long id;
    private String name;
    private BigDecimal factor;
    private Boolean isArchived;
    private Date updateDate;
}
