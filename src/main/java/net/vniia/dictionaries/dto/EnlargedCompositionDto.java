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
public class EnlargedCompositionDto implements Serializable {
    private Long id;
    private String designation;
    private String vshi;
    private String name;
    private String note;
    private Date updateDate;
    private String updateUser;
}
