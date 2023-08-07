package by.itacademy.taskmanager.userservice.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UpdateParamsDTO {

    private UUID uuid;

    private LocalDateTime version;
}
