package by.itacademy.taskmanager.audit_service.core.exceptions.custom;

public class CustomValidationException extends RuntimeException{

    CustomValidationException(){
        super();
    }
    CustomValidationException(String message) {
        super(message);
    }
}
