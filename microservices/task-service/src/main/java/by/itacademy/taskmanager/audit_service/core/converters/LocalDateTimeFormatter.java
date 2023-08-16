package by.itacademy.taskmanager.audit_service.core.converters;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Objects;

@AllArgsConstructor

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private final ConversionService conversionService;

    @Override
    @NonNull
    public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
        return Objects.requireNonNull(conversionService.convert(Long.valueOf(text), LocalDateTime.class));
    }

    @Override
    @NonNull
    public String print(LocalDateTime object, @NonNull Locale locale) {
        return String.valueOf(object.toEpochSecond(ZoneOffset.UTC));
    }
}
