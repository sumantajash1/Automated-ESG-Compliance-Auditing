package com.sumanta.HackFest.Exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String id) {
        super("Client is not found with this given credentials. ID : " + id);
    }
}
