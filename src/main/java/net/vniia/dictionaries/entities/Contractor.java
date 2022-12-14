package net.vniia.dictionaries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DICT_CONTRACTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contractor {
    @Id
    private Long id;
    private Long parentId;
    private Long countryId;
    private Long countryCode;
    private String countryName;
    private String type;
    private Boolean isClient;
    private Boolean isSupplier;
    private Boolean isCargoShipper;
    private Boolean isCargoReceiver;
    private Long ownerTypeId;
    private String ownerTypeName;
    private String name;
    private String fullName;
    private String localName;
    private String inn;
    private String kpp;
    private String legalAddress;
    private String actualAddress;
    private Date createDate;
    private Date archiveDate;
    private Boolean isArchived;
    private Date updateDate;
}
