package by.itacademy.taskmanager.userservice.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class EpochToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull Long source) {
        return Instant.ofEpochMilli(source)
                .atZone(ZoneId.of("UTC"))
                .toLocalDateTime()
                .truncatedTo(ChronoUnit.MILLIS);
    }
}
