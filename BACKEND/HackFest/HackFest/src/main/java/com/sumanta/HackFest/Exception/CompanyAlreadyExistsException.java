package com.sumanta.HackFest.Exception;

public class CompanyAlreadyExistsException extends RuntimeException {
  public CompanyAlreadyExistsException(String credentialType) {
    super("Another company with similar " + credentialType + " already exists");
  }
}
