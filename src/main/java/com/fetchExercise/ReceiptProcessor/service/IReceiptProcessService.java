package com.fetchExercise.ReceiptProcessor.service;

import com.fetchExercise.ReceiptProcessor.models.PointsRespose;
import com.fetchExercise.ReceiptProcessor.models.ProcessResponse;
import com.fetchExercise.ReceiptProcessor.models.Receipt;

import java.util.List;
import java.util.UUID;

/**
 * Service interface
 */
public interface IReceiptProcessService {
    public ProcessResponse receiptProcess(Receipt receipt);
    public PointsRespose points(UUID id);


}
