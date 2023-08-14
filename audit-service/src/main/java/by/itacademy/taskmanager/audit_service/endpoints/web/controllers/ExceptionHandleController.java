package by.itacademy.taskmanager.audit_service.endpoints.web.controllers;

import by.itacademy.taskmanager.audit_service.core.exceptions.custom.CustomValidationException;
import by.itacademy.taskmanager.audit_service.core.exceptions.error_responses.NestedErrorResponse;
import by.itacademy.taskmanager.audit_service.core.exceptions.error_responses.SingleErrorResponse;
import by.itacademy.taskmanager.audit_service.core.exceptions.error_responses.StructuredErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;

@RequiredArgsConstructor

@RestControllerAdvice
public class ExceptionHandleController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Server error. Please contact the administrator";
    private static final String NOT_READABLE_ERROR_MESSAGE = "Http message is incorrect. Please try again";
    private static final String INCORRECT_TYPE = "Incorrect type of value";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<List<SingleErrorResponse>> handleAll(Exception ex) {
        List<SingleErrorResponse> errors= new ArrayList<>();
        errors.add(new SingleErrorResponse(INTERNAL_SERVER_ERROR_MESSAGE));
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<StructuredErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<NestedErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new NestedErrorResponse(errorMessage, fieldName));
        });
        return new ResponseEntity<>(new StructuredErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public final ResponseEntity<StructuredErrorResponse> handleTypeMismatch(TypeMismatchException ex) {

        List<NestedErrorResponse> errors = new ArrayList<>();
        errors.add(new NestedErrorResponse(INCORRECT_TYPE, ex.getPropertyName()));
        return new ResponseEntity<>(new StructuredErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<List<SingleErrorResponse>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(List.of(new SingleErrorResponse(NOT_READABLE_ERROR_MESSAGE)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<StructuredErrorResponse> handleConstraintViolation(ConstraintViolationException ex)
            throws NoSuchFieldException {

        List<NestedErrorResponse> errors = new ArrayList<>();

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            String fieldName = constraintViolation
                    .getRootBeanClass()
                    .getDeclaredField(constraintViolation
                            .getPropertyPath()
                            .toString())
                    .getAnnotation(JsonProperty.class)
                    .value();
            errors.add(new NestedErrorResponse(constraintViolation.getMessage(), fieldName));
        }
        return new ResponseEntity<>(new StructuredErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public final ResponseEntity<List<SingleErrorResponse>> handleCustomValidation(CustomValidationException ex){
        return new ResponseEntity<>(List.of(new SingleErrorResponse(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
}
