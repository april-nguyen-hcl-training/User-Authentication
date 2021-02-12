package com.hcl.authentication.controller;

import com.hcl.authentication.exception.UserNotAuthenticatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionController extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ UserNotAuthenticatedException.class })
  protected ResponseEntity<Object> handleNotAuthenticated(Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
      new HttpHeaders(), HttpStatus.valueOf(401), request);
  }
}
