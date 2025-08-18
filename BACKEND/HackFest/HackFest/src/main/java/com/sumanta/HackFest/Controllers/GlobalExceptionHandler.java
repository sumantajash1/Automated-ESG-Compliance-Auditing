package com.sumanta.HackFest.Controllers;

import com.sumanta.HackFest.DTO.ApiResponse;
import com.sumanta.HackFest.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericExceptions(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
    public ResponseEntity<ApiResponse<Void>> handleClientNotFoundException(ClientNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
    public ResponseEntity<ApiResponse<Void>> handleCompanyNotFound(CompanyNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
    public ResponseEntity<ApiResponse<Void>> handleCompanyAlreadyExistsException(CompanyAlreadyExistsException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
    public ResponseEntity<ApiResponse<Void>> handleClientAlreadyExistsException(ClientAlreadyExistsException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
    public ResponseEntity<ApiResponse<Void>> handleGovernmentNotFoundException(GovernmentNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse<>(
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
