package com.sumanta.HackFest.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    @Column(name = "gstnumber")
    private String gstNumber;
    @Column(name = "suppliername")
    private String supplierName;
    @Column(name = "email")
    private String email;
    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role = Role.SUPPLIER;

    public Supplier() {
    }

    public Supplier(String gstNumber, String supplierName, String email, String contactNumber, String password, Role role) {
        this.gstNumber = gstNumber;
        this.supplierName = supplierName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        return "Supplier{" +
                "gstNumber='" + gstNumber + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
