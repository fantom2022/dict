package net.vniia.dictionaries.entities.enums;

public enum MaterialResourceType {
    OUTER_PRODUCTS(101L),
    FULL_PRODUCTS(102L),
    EQUIPS(103L),
    TOOLS(104L),
    HARDWARE(105L),
    FINISHED_PRODUCTS(106L),
    PRECIOUS_METALS(107L),
    MAINTENANCE_DEPARTMENT(108L);

    private final Long value;

    MaterialResourceType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }
}
