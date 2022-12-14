package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentExemplarDto implements Serializable {
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
