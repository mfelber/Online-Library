package com.example.OnlineLibrary.exception;

public class OperationNotPermittedException extends RuntimeException {

  public OperationNotPermittedException(final String s) {
    super(s);
  }

}
