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
public class ContractorDto implements Serializable {
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
