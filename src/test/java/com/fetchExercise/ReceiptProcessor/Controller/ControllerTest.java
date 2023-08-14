package com.fetchExercise.ReceiptProcessor.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchExercise.ReceiptProcessor.controller.ReceiptProcessorControlller;
import com.fetchExercise.ReceiptProcessor.models.PointsRespose;
import com.fetchExercise.ReceiptProcessor.models.ProcessResponse;
import com.fetchExercise.ReceiptProcessor.models.Receipt;
import com.fetchExercise.ReceiptProcessor.service.IReceiptProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.fetchExercise.ReceiptProcessor.TestUtils.*;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceiptProcessorControlller.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    IReceiptProcessService iReceiptProcessService;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void receiptTest_Success() throws Exception{
        Receipt request = createReceipt();
        ObjectMapper mapper = new ObjectMapper();
        ProcessResponse responseMock = new ProcessResponse();
        when(iReceiptProcessService.receiptProcess(any(Receipt.class))).thenReturn(responseMock);

        this.mockMvc.perform(
                        post("/receipts/process")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(request)))
                                .andExpect(status().isCreated());
    }@Test
    void receiptTest_BadRequest() throws Exception{
        Receipt request = new Receipt();
        ObjectMapper mapper = new ObjectMapper();
        ProcessResponse responseMock = new ProcessResponse();
        when(iReceiptProcessService.receiptProcess(any(Receipt.class))).thenReturn(responseMock);

        this.mockMvc.perform(
                        post("/receipts/process")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(request)))
                                .andExpect(status().isBadRequest());
    }


    @Test
    void receiptPontsTest_Success() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        PointsRespose responseMock = new PointsRespose();
        when(iReceiptProcessService.points(any(UUID.class))).thenReturn(responseMock);

        this.mockMvc.perform(
                        get("/receipts/3efe7496-e7b5-4260-bfa1-a0242ffb6edf/points")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
