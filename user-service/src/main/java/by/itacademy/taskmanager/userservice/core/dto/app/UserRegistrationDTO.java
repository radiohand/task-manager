package by.itacademy.taskmanager.userservice.core.dto.app;

import by.itacademy.taskmanager.userservice.core.validators.annotations.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserRegistrationDTO {
    @JsonProperty("mail")
    @NotBlank(message = "Value can't be blank")
    @UniqueEmail
    private String email;

    @JsonProperty("fio")
    @NotBlank(message = "Value can't be blank")
    private String fio;

    @JsonProperty("password")
    @NotBlank(message = "Value can't be blank")
    @Size(min = 6, message = "Too short value")
    @Size(max = 12, message = "Too long value")
    private String password;
}
