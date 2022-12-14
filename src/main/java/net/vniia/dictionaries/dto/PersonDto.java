package net.vniia.dictionaries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
}
