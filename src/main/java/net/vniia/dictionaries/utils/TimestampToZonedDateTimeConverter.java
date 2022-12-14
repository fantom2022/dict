package net.vniia.dictionaries.utils;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestampToZonedDateTimeConverter extends StdConverter<Long, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Long timestamp) {
        return timestamp == null
                ? null
                : Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault());
    }
}
