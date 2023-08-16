package by.itacademy.taskmanager.audit_service.core.exceptions.custom;

public class AccessDeniedException extends CustomValidationException{
    public AccessDeniedException(){
        super("Access denied");
    }

    public AccessDeniedException(String message){
        super(message);
    }
}
