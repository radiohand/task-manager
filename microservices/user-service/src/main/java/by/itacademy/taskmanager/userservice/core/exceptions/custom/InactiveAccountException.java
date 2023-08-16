package by.itacademy.taskmanager.userservice.core.exceptions.custom;

public class InactiveAccountException extends CustomValidationException{
    public InactiveAccountException() {
        super("This account is inactive");
    }

    public InactiveAccountException(String message) {
        super(message);
    }
}
