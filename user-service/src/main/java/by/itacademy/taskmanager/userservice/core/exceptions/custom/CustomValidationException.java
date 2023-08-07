package by.itacademy.taskmanager.userservice.core.exceptions.custom;

public class CustomValidationException extends RuntimeException{

    CustomValidationException(){
        super();
    }
    CustomValidationException(String message) {
        super(message);
    }
}
