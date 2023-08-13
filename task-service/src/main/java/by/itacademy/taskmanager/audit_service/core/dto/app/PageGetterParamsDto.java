package by.itacademy.taskmanager.audit_service.core.dto.app;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PageGetterParamsDto {

    @Min(value = 0, message = "Value must be 0 or positive")
    private Integer page;

    @Positive(message = "Value must be positive")
    private Integer size;
}
