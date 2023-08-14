package com.fetchExercise.ReceiptProcessor.Service;


import com.fetchExercise.ReceiptProcessor.Dao.IReceiptProcessDao;
import com.fetchExercise.ReceiptProcessor.Exception.ResourceNotFoundException;
import com.fetchExercise.ReceiptProcessor.models.Item;
import com.fetchExercise.ReceiptProcessor.models.PointsRespose;
import com.fetchExercise.ReceiptProcessor.models.ProcessResponse;
import com.fetchExercise.ReceiptProcessor.models.Receipt;
import com.fetchExercise.ReceiptProcessor.service.IReceiptProcessService;
import com.fetchExercise.ReceiptProcessor.service.ReceiptProcessServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.fetchExercise.ReceiptProcessor.Constants.Constants.message404;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.fetchExercise.ReceiptProcessor.TestUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ServiceTest {
    @Autowired
    @InjectMocks
    private ReceiptProcessServiceImpl service;

    @Mock
    @Autowired
    private IReceiptProcessDao dao;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Receipt responsemock = new Receipt();
        responsemock.setId(UUID.randomUUID());
        Mockito.when(this.dao.save(any(Receipt.class))).thenReturn(responsemock);

    }

    @Test
    void receiptProcessTestSuccess(){
        Receipt receipt = new Receipt();
        receipt.setItems(loadItems());
        ProcessResponse response = this.service.receiptProcess(receipt);
        assertNotNull(response.getId());
    }

    @Test
    void pointsTestSuccess(){
        Receipt responseMock2 = new Receipt();
        responseMock2 = createReceipt();
        Mockito.when(this.dao.findById(any(UUID.class))).thenReturn(Optional.of(responseMock2));
        PointsRespose response = this.service.points(UUID.randomUUID());
        assertEquals(14,response.getPoints());
    }

    @Test
    void pointsTestNotfound(){
        Mockito.when(this.dao.findById(any(UUID.class))).thenThrow(new ResourceNotFoundException(message404+"1"));
        try{
            PointsRespose response = this.service.points(UUID.randomUUID());
        }catch(Exception e){
            assertEquals(message404+1,e.getMessage());
        }
    }

}
