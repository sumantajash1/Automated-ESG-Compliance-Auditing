package com.sumanta.HackFest.DTO;

import org.springframework.stereotype.Component;

@Component
public class SupplierDto {
    private String gstNumber;
    private String supplierName;
    private String email;
    private String contactNumber;

    public SupplierDto() {
    }

    public SupplierDto(String gstNumber, String supplierName, String email, String suppliername) {
        this.contactNumber = suppliername;
        this.gstNumber = gstNumber;
        this.supplierName = supplierName;
        this.email = email;
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

    public String getSupplierEmail() {
        return email;
    }

    public void setSupplierEmail(String suppliername) {
        this.supplierName = suppliername;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "gstNumber='" + gstNumber + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
