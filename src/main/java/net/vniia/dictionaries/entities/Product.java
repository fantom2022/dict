package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dict_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    private String designation;
    private String name;
    private Long typeId;
    private Long productIdMainVariant;
    private String inventoryNumberTechdocs;
}
