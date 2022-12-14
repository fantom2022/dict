package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
