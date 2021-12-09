package com.pt.pedrovcruzeiro.phonebook.util.error;

public class InvalidPhoneNumberException extends RuntimeException {

  public InvalidPhoneNumberException(String entity) {
    super(entity);
  }
}
