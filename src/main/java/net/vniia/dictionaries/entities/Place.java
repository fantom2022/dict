package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_PLACE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    private Long id;
    private String name;
    private String fullName;
}
