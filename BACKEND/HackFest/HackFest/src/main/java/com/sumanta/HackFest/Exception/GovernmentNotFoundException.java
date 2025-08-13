package com.sumanta.HackFest.Exception;

public class GovernmentNotFoundException extends RuntimeException {
  public GovernmentNotFoundException(String id) {
            super("No Government is to be found with the given credentials. ID : " + id);
  }
}
