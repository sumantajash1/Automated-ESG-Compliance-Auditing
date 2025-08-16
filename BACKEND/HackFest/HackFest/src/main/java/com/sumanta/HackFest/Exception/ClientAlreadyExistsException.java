package com.sumanta.HackFest.Exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String credentialType) {
        super("Another account already exists with client's " + credentialType);
    }
}
