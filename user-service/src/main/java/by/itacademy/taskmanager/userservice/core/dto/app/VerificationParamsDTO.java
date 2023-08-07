package by.itacademy.taskmanager.userservice.core.dto.app;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VerificationParamsDTO {

    @NotBlank(message = "Value can't be blank")
    @Min(value = 100_000, message = "Incorrect size of verification code")
    @Max(value = 999_999, message = "Incorrect size of verification code")

    private Integer code;

    @NotBlank(message = "Value can't be blank")
    @Email(message = "Incorrect format of email")
    private String mail;
}
