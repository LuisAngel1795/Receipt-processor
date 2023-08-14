package com.fetchExercise.ReceiptProcessor.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * Definition of a class that handles the exceptions thrown by the controller and the service
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception thrown when the id doesn't exist.
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Exception thrown when the request is bad
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = "The receipt is invalid";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}

