package com.example.crud.dto;

import java.time.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@Setter
public class ApiExceptionDto {

    private HttpStatus status;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    public ApiExceptionDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
