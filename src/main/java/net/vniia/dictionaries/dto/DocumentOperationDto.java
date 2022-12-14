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
public class DocumentOperationDto implements Serializable {
    private Long id;
    private String name;
    private Date updateDate;

    public DocumentOperationDto(Long id) {
        this.id = id;
    }
}
