package com.example.OnlineLibrary.handler;

import static com.example.OnlineLibrary.handler.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.OnlineLibrary.exception.OperationNotPermittedException;

import jakarta.mail.MessagingException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ExceptionResponse> handleException(LockedException exception){
    return ResponseEntity.status(UNAUTHORIZED)
        .body(ExceptionResponse
            .builder()
            .errorCode(ACCOUNT_LOCKED.getCode())
            .errorDescription(ACCOUNT_LOCKED.getDescription())
            .error(exception.getMessage())
            .build());
  }

  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ExceptionResponse> handleException(DisabledException exception){
    return ResponseEntity.status(UNAUTHORIZED)
        .body(ExceptionResponse
            .builder()
            .errorCode(ACCOUNT_DISABLED.getCode())
            .errorDescription(ACCOUNT_DISABLED.getDescription())
            .error(exception.getMessage())
            .build());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception){
    return ResponseEntity.status(UNAUTHORIZED)
        .body(ExceptionResponse
            .builder()
            .errorCode(BAD_CREDENTIALS.getCode())
            .errorDescription(BAD_CREDENTIALS.getDescription())
            .error(BAD_CREDENTIALS.getDescription())
            .build());
  }

  @ExceptionHandler(MessagingException.class)
  public ResponseEntity<ExceptionResponse> handleException(MessagingException exception){
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(ExceptionResponse
            .builder()
            .error(exception.getMessage())
            .build());
  }

  @ExceptionHandler(OperationNotPermittedException.class)
  public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exception){
    return ResponseEntity.status(BAD_REQUEST)
        .body(ExceptionResponse
            .builder()
            .error(exception.getMessage())
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception){

    Set<String> errors = new HashSet<>();
    exception.getBindingResult().getAllErrors().forEach(error -> {
      var errorMessage = error.getDefaultMessage();
      errors.add(errorMessage);
    });

    return ResponseEntity.status(BAD_REQUEST)
        .body(ExceptionResponse
            .builder()
            .validationErrors(errors)
            .build());
  }

  // handle any type of exception which is not handled by errorsCodes from Enum ErrorCodes
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(Exception exception){
    exception.printStackTrace();
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(ExceptionResponse
            .builder()
            .errorDescription("Internal error, contact support")
            .error(exception.getMessage())
            .build());
  }

}
