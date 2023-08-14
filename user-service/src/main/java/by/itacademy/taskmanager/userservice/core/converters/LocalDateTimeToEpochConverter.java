package by.itacademy.taskmanager.userservice.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class LocalDateTimeToEpochConverter implements Converter <LocalDateTime, Long> {

    @Override
    public Long convert(@NonNull LocalDateTime source) {
        return ZonedDateTime.of(source, ZoneOffset.UTC).toInstant().toEpochMilli();
    }
}
