package com.devictoralmeida.spring_mongodb.controllers.handlers;

import com.devictoralmeida.spring_mongodb.controllers.exceptions.StandardError;
import com.devictoralmeida.spring_mongodb.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    StandardError err = new StandardError();
    err.setError("Not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    err.setStatus(status.value());
    err.setTimestamp(Instant.now());
    return ResponseEntity.status(status).body(err);
  }
}
