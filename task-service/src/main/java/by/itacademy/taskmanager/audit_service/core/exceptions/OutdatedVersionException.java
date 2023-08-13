package by.itacademy.taskmanager.audit_service.core.exceptions;

public class OutdatedVersionException extends CustomValidationException {

    public OutdatedVersionException(String message) {
        super(message);
    }
    public OutdatedVersionException() {
        super("The version is out of date");
    }
}
