package net.vniia.dictionaries.common.audit;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.vniia.dictionaries.dto.PersonViewDto;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AuditableDto {
    private PersonViewDto createdByPerson;
    private Instant createdDate;
    private PersonViewDto modifiedByPerson;
    private Instant modifiedDate;
}
