package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_DOCUMENT_OPERATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentOperation {
    @Id
    private Long id;
    private String name;
    private Date updateDate;
}
