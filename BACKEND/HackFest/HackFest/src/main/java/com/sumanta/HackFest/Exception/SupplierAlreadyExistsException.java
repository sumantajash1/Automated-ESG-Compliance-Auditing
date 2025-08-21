package com.sumanta.HackFest.Exception;

public class SupplierAlreadyExistsException extends RuntimeException {
  public SupplierAlreadyExistsException(String credentialType) {
    super("Another company with similar " + credentialType + " already exists");
  }
}
