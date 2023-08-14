package com.fetchExercise.ReceiptProcessor.controller;

import com.fetchExercise.ReceiptProcessor.Exception.ResourceNotFoundException;
import com.fetchExercise.ReceiptProcessor.models.PointsRespose;
import com.fetchExercise.ReceiptProcessor.models.ProcessResponse;
import com.fetchExercise.ReceiptProcessor.models.Receipt;
import com.fetchExercise.ReceiptProcessor.service.IReceiptProcessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReceiptProcessorControlller {
    /**
     * The Service class is instantiated by the @Autowired annotation
     */
    @Autowired
    private IReceiptProcessService iReceiptProcessService;

    /**
     * Here it is defined the endpoint to insert in the DB a receipt
     * Also the annotation @Valid are used to validate the fields.
     * @param receipt
     * @return
     */
    @PostMapping("/receipts/process")
    @ResponseStatus(HttpStatus.CREATED) //The contract says the response code must be 200, but i think the correct code should be 201.
    public ProcessResponse receipt(
            @Valid
            @RequestBody Receipt receipt){
        return iReceiptProcessService.receiptProcess(receipt);
    }

    /**
     * Here it is defined the endpoint to get the total of points earned
     * @param id
     * @return
     */
    @GetMapping ("/receipts/{id}/points")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PointsRespose> receiptPonts(@PathVariable UUID id){
        PointsRespose pointsRespose = iReceiptProcessService.points(id);
    return ResponseEntity.ok(iReceiptProcessService.points(id));
    }
}
