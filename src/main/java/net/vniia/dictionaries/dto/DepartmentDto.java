package net.vniia.dictionaries.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class DepartmentDto implements Serializable {
    private Long id;
    private Long alfaId;
    private String code;
    private String name;
    private String fullName;
    private Long parentId;
}
