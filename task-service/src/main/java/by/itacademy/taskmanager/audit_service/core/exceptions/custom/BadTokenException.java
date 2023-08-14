package by.itacademy.taskmanager.audit_service.core.exceptions;

public class BadTokenException extends CustomValidationException{
    public BadTokenException() {
        super("Bad token");
    }

    public BadTokenException(String message) {
        super(message);
    }
}
