package com.sumanta.HackFest.Exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String id) {
        super("No company is to be found with the given credentials. ID : " + id);
    }
}
