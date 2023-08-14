package by.itacademy.taskmanager.userservice.core.exceptions.custom;

public class BadTokenException extends CustomValidationException{
    public BadTokenException() {
        super("Bad token");
    }

    public BadTokenException(String message) {
        super(message);
    }
}
