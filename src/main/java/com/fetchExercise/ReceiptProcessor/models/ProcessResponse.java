package com.fetchExercise.ReceiptProcessor.models;

import java.util.UUID;

/**
 * Definition of the entity that represents the id generated for the receipt
 */
public class ProcessResponse {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
