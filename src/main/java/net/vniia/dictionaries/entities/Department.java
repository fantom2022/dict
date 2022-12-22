package net.vniia.dictionaries.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_DEPARTMENT")
@Data
@NoArgsConstructor
public class Department {
    @Id
    private Long id;
    private String code;
    private String name;
    private String fullName;
    private Long typeId;
    private Date closeDate;
    private Long parentId;
    private Long placeId;
    private Date updateDate;
}
