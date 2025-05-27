package com.example.OnlineLibrary.handler;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public enum ErrorCodes {

  NO_CODE(0, NOT_IMPLEMENTED,"No code"),
  INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Incorrect current password"),
  NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"New password does not match"),
  ACCOUNT_LOCKED(302, FORBIDDEN, "Account locked"),
  ACCOUNT_DISABLED(303,BAD_REQUEST,"Account disabled"),
  BAD_CREDENTIALS(304,BAD_REQUEST,"Login or password is incorrect"),
  ;
  @Getter
  private final int code;
  @Getter
  private final  String description;
  @Getter
  private final HttpStatus httpStatus;

  ErrorCodes(int code, HttpStatus httpStatus, String description) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.description = description;
  }

}
