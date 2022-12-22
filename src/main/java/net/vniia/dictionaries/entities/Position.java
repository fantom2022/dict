package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_POSITION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    private Long id;
    private String code;
    private String name;
    private Date endDate;
}
