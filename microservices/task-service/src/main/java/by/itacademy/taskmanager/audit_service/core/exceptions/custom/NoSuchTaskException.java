package by.itacademy.taskmanager.audit_service.core.exceptions.custom;

public class NoSuchTaskException extends CustomValidationException{
    public NoSuchTaskException(){
        super("There is no task with defined uuid");
    }

    public NoSuchTaskException(String message){
        super(message);
    }
}
