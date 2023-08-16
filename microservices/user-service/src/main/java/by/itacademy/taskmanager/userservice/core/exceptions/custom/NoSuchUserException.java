package by.itacademy.taskmanager.userservice.core.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")
public class NoSuchUserException extends CustomValidationException{
    public NoSuchUserException(){
        super("There is no user with specified uuid");
    }

    public NoSuchUserException(String message){
        super(message);
    }
}
