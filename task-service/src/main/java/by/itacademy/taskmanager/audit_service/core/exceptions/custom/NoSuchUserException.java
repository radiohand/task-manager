package by.itacademy.taskmanager.audit_service.core.exceptions;

public class NoSuchUserException extends CustomValidationException{
    public NoSuchUserException(){
        super("There is no user with defined uuid");
    }

    public NoSuchUserException(String message){
        super(message);
    }
}
