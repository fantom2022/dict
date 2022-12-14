package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="DICT_ENLARGED_COMPOSITION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnlargedComposition {
    @Id
    private Long id;
    private String designation;
    private String vshi;
    private String name;
    private String note;
    private Date updateDate;
    private String updateUser;
}
