package com.fetchExercise.ReceiptProcessor.Exception;

/**
 * Definition of a custom exception (an exception that represents HTTP status code 404)
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
