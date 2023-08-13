package by.itacademy.taskmanager.audit_service.core.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class LocalDateTimeToEpochConverter implements Converter <LocalDateTime, Long> {

    @Override
    public Long convert(LocalDateTime source) {
        return ZonedDateTime.of(source, ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
