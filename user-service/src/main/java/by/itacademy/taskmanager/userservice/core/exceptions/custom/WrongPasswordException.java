package by.itacademy.taskmanager.userservice.core.exceptions.custom;

public class WrongPasswordException extends CustomValidationException{
    public WrongPasswordException(){
        super("Wrong Password");
    }

    public WrongPasswordException(String message){
        super(message);
    }
}
