package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResourceDto implements Serializable {
    private Long id;
    private Long idInner;
    private ReferenceDto reference;
    private String designation;
    private String name;
    private Date updateDate;

}
