package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.entities.enums.CalendarType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_CALENDAR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {
    @Id
    private Long id;
    private Date updateDate;
    private Date date;
    private CalendarType type;
}
