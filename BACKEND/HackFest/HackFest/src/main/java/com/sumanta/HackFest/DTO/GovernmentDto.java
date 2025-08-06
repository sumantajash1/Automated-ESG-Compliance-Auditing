package com.sumanta.HackFest.DTO;

import org.springframework.stereotype.Component;

@Component
public class GovernmentDto {
    private String EmployeeId;
    private String EmployeeName;
    private String contactNumber;
    private String email;

    public GovernmentDto() {
    }

    public GovernmentDto(String employeeId, String employeeName, String contactNumber, String email) {
        EmployeeId = employeeId;
        EmployeeName = employeeName;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GovernmentDto{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
