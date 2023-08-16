package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.ProjectRefDto;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.util.Locale;
import java.util.UUID;

@AllArgsConstructor

public class ProjectRefDtoFormatter implements Formatter<ProjectRefDto> {


    @Override
    @NonNull
    public ProjectRefDto parse(@NonNull String text, @NonNull Locale locale) {
        ProjectRefDto refDto = new ProjectRefDto();
        refDto.setUuid(UUID.fromString(text));
        return refDto;
    }

    @Override
    @NonNull
    public String print(ProjectRefDto object, @NonNull Locale locale) {
        return String.valueOf(object.getUuid().toString());
    }
}
