package net.vniia.dictionaries.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "DICT_DESIGNATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Designation {
    @Id
    private Long id;
    private String designation;
    private String name;
    private LocalDate updateDate;
    private String updateUser;
}
