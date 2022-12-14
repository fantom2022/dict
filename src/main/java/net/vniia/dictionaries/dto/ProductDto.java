package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private Long id;
    private String designation;
    private String name;
    private Long typeId;
    private Long productIdMainVariant;
    private String inventoryNumberTechdocs;
}
