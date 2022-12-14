package net.vniia.dictionaries.entities.enums;

public enum CalendarType {
    WORK_DAY(0L),   // рабочий день
    OFF_DAY(1L),    // выходной
    HIGH_DAY(2L);   // праздничный день

    private Long value;
    CalendarType(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
