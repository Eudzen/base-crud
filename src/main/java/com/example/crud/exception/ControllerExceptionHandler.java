package com.example.crud.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    //TODO: заменить возвращаемые значения на соответствующие DTOs
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException exception) {
        return "Exception message: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public String handleAlreadyExistsException(AlreadyExistsException exception) {
        return "Exception message: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectArgumentException.class)
    public String handleIncorrectArgumentException(IncorrectArgumentException exception) {
        return "Exception message: " + exception.getMessage();
    }

}
