package by.itacademy.taskmanager.audit_service.core.exceptions.custom;

public class NoSuchProjectException extends CustomValidationException{
    public NoSuchProjectException(){
        super("There is no project with defined uuid");
    }

    public NoSuchProjectException(String message){
        super(message);
    }
}
