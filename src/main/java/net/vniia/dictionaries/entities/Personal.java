package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_PERSONAL")
@Data
@NoArgsConstructor
public class Personal {
    @Id
    private Long id;
    private String personalNumber;
    private Long personId;
    private Date beginDate;
    private Date dismissedDate;
    private Long workType;
    private Long departmentId;
    private Long positionId;
    private Long placeId;
}
