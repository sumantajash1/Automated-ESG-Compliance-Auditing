package com.sumanta.HackFest.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "government")
public class Government {
    @Id
    @Column(name = "employeeid")
    private String employeeId;
    @Column(name = "employeename")
    private String employeeName;
    @Column(name = "email")
    private String email;
    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    Role role = Role.GOVERNMENT;

    public Government() {
    }

    public Government(String employeeId, String employeeName, String email, String contactNumber, String password, Role role) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Government{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
