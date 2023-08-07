package by.itacademy.taskmanager.userservice.core.exceptions.custom;

public class OutdatedVersionException extends CustomValidationException{
    public OutdatedVersionException() {
        super();
    }
    public OutdatedVersionException(String message) {
        super(message);
    }
}
