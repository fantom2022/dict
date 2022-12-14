package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.entities.enums.CalendarType;
import net.vniia.dictionaries.services.CalendarService;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto {
    private Long id;
    private Date updateDate;
    private Date date;
    private CalendarType type;

    public CalendarDto(Date date, CalendarType type) {
        this.date = date;
        this.type = type;
    }

    public LocalDate getLocalDate() {
        return Optional.ofNullable(this.date).map(CalendarService::toLocalDate).orElse(null);
    }
}
