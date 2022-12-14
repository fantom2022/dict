package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "DICT_EQUIPMENT_EXEMPLAR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentExemplar {
    @Id
    private Long id;
    private String designation;
    private String name;
    private String placeName;
    private String inventoryNumber;
    private Long departmentId;
    private String departmentCode;
    private String buildingNumber;
    private String roomNumber;
    private String notes;
    private Long idArea;
    private String nameArea;
    private LocalDate updateDate;
}
