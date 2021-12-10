package com.pt.pedrovcruzeiro.phonebook.util.error;

public class ValidationException extends RuntimeException {

  public ValidationException(String entity) {
    super(entity);
  }
}
