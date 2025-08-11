package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ResponseWrapper;
import com.sumanta.HackFest.Exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleGenericExceptions(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        500,
                        "Internal Server Error",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleClientNotFoundException(ClientNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        400,
                        "User Not Found.",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
