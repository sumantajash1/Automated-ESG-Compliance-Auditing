package com.sumanta.HackFest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ApiResponse<T> {
    boolean success;
    int httpStatusCode;
    String message;
    T data;
    String error;

    public static <T> ApiResponse<T> ok(T data, String message) {
        return new ApiResponse<>(
                true,
                200,
                message,
                data,
                null
        );
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(
                true,
                201,
                message,
                data,
                null
        );
    }
}
