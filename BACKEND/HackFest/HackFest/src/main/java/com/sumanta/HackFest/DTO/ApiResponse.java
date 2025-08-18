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
}
