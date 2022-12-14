package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
