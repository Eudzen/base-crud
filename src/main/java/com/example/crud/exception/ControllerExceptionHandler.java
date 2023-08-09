package com.example.crud.exception;

import java.util.stream.*;

import com.example.crud.dto.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ApiResponse(responseCode = "404",
        useReturnTypeSchema = true,
        description = "Объект не найден",
        content = @Content(examples =
        @ExampleObject(name = "Пример ошибки",
            value = "{\"status\": \"NOT_FOUND\",\"timestamp\": \"08-08-2008 08:08:08\","
                    + "\"message\": \"Объект не был найден.\"}"))
    )
    public @ResponseBody ApiExceptionDto handleNotFoundException(NotFoundException exception) {
        return new ApiExceptionDto(
            HttpStatus.NOT_FOUND,
            "Exception message: " + exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    @ApiResponse(responseCode = "409",
        useReturnTypeSchema = true,
        description = "Объект уже существует",
        content = @Content(examples =
        @ExampleObject(name = "Пример ошибки",
            value = "{\"status\": \"CONFLICT\",\"timestamp\": \"08-08-2008 08:08:08\","
                    + "\"message\": \"Объект уже существует.\"}"))
    )
    public @ResponseBody ApiExceptionDto handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ApiExceptionDto(
            HttpStatus.CONFLICT,
            "Exception message: " + exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectArgumentException.class)
    @ApiResponse(responseCode = "400",
        useReturnTypeSchema = true,
        description = "Ошибки валидации.",
        content = @Content(examples =
        @ExampleObject(name = "Пример ошибки",
            value = "{\"status\": \"BAD_REQUEST\",\"timestamp\": \"08-08-2008 08:08:08\","
                    + "\"message\": \"Что-либо не прошло валидацию.\"}"))
    )
    public @ResponseBody ApiExceptionDto handleIncorrectArgumentException(IncorrectArgumentException exception) {
        return new ApiExceptionDto(
            HttpStatus.BAD_REQUEST,
            "Exception message: " + exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "400",
        useReturnTypeSchema = true,
        description = "Ошибки валидации.",
        content = @Content(examples =
        @ExampleObject(name = "Пример ошибки",
            value = "{\"status\": \"BAD_REQUEST\",\"timestamp\": \"08-08-2008 08:08:08\","
                    + "\"message\": \"Что-либо не прошло валидацию.\"}"))
    )
    @ResponseBody
    ApiExceptionDto handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception
    ) {
        var errors = exception.getBindingResult().getFieldErrors();
        String wholeMessage = errors.stream()
            .map(e ->
                String.format("%s: %s", e.getField(), e.getDefaultMessage()))
            .collect(Collectors.joining("\n"));

        return new ApiExceptionDto(
            HttpStatus.BAD_REQUEST,
            "Exception message:\n" + wholeMessage
        );
    }

}
