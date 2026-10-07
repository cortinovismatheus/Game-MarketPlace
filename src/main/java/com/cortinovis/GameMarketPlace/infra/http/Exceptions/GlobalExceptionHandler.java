package com.cortinovis.GameMarketPlace.infra.http.Exceptions;

import com.cortinovis.GameMarketPlace.domain.Exceptions.NotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(
          @NonNull NotFoundException exception) {

    ExceptionStatus status = ExceptionStatus.NOT_FOUND;

    return ResponseEntity
            .status(status.getStatus())
            .body(Map.of(
                    "message", exception.getMessage()
            ));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<?> handleUrlNotFound(
          @NonNull NoResourceFoundException exception) {

    return ResponseEntity
            .status(404)
            .body(Map.of(
                    "message", "Url not found!"
            ));
  }
}