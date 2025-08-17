package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ResponseWrapper;
import com.sumanta.HackFest.Exception.*;
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
                        404,
                        "User Not Found.",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleCompanyNotFound(CompanyNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        404,
                        "User Not Found.",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleCompanyAlreadyExistsException(CompanyAlreadyExistsException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        409,
                        "Similar credential",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleClientAlreadyExistsException(ClientAlreadyExistsException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        409,
                        "Similar Credential",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(GovernmentNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Void>> handleGovernmentNotFoundException(GovernmentNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ResponseWrapper<>(
                        false,
                        404,
                        "User Not Found.",
                        null,
                        exception.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
