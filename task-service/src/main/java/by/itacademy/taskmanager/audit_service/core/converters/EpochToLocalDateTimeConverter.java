package by.itacademy.taskmanager.audit_service.core.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class EpochToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(Long source) {
        return Instant.ofEpochMilli(source)
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime()
                .truncatedTo(ChronoUnit.SECONDS);
    }
}
