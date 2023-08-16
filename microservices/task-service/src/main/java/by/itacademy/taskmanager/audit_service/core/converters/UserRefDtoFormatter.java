package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.util.Locale;
import java.util.UUID;

@AllArgsConstructor

public class UserRefDtoFormatter implements Formatter<UserRefDto> {

    @Override
    @NonNull
    public UserRefDto parse(@NonNull String text, @NonNull Locale locale) {
        UserRefDto dto = new UserRefDto();
        dto.setUuid(UUID.fromString(text));
        return dto;
    }

    @Override
    @NonNull
    public String print(UserRefDto object, @NonNull Locale locale) {
        return String.valueOf(object.getUuid().toString());
    }
}
