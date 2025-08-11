package com.sumanta.HackFest.Exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String id) {
        super("Client is not found with this given credentials. ID : " + id);
    }
}
