package by.itacademy.taskmanager.audit_service.core.exceptions;

public class NoSuchAuditException extends CustomValidationException{
    public NoSuchAuditException(){
        super("There is no audit with defined uuid");
    }

    public NoSuchAuditException(String message){
        super(message);
    }
}
