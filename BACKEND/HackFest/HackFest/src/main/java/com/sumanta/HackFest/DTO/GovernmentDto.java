package com.sumanta.HackFest.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class GovernmentDto {
    private String EmployeeId;
    private String EmployeeName;
    private String contactNumber;
    private String email;
}
