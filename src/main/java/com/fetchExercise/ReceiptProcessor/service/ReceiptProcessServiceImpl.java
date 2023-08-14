package com.fetchExercise.ReceiptProcessor.service;


import com.fetchExercise.ReceiptProcessor.Dao.IItemDao;
import com.fetchExercise.ReceiptProcessor.Dao.IReceiptProcessDao;
import com.fetchExercise.ReceiptProcessor.Exception.ResourceNotFoundException;
import com.fetchExercise.ReceiptProcessor.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.fetchExercise.ReceiptProcessor.Constants.Constants.*;

import java.util.*;

/**
 * Implementation of the service interface. Contains the bussiness rules logic
 */
@Service
public class ReceiptProcessServiceImpl implements IReceiptProcessService {

    @Autowired
    private IReceiptProcessDao IReceiptProcessDao;
    @Autowired
    private IItemDao iItemDao;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private Double total;
    private int points = 0;

    /**
     * A method that is called by the controller. Inserts into the database the receipt
     * received in the request body.
     * @param receipt
     * @return
     */
    @Override
    public ProcessResponse receiptProcess(Receipt receipt) {
        List<Item> items = receipt.getItems();
        receipt.setItems(new ArrayList<>());
        for (Item item: items) {
            /**
             * find the items
             */
            Item item1 = iItemDao.findItemByShortDescription(item.getShortDescription());
            if (item1 == null) {
                item1 = iItemDao.save(item);
            }
            receipt.getItems().add(item1);
        }
        ProcessResponse processResponse = new ProcessResponse();
        processResponse.setId(IReceiptProcessDao.save(receipt).getId());
        return processResponse;
    }

    /**
     * A method that is called by the controller. This method calculate the earned points
     * following all the bussiness rules
     * @param id
     * @return
     */
    @Override
    public PointsRespose points(UUID id) {
        Receipt receipt = IReceiptProcessDao.findById(id).orElseThrow(()
                        -> new ResourceNotFoundException(message404+id));
        /**
         * One point for every alphanumeric character in the retailer name
         */
        retailer = receipt.getRetailer();
        points = retailer.replaceAll("[^\\dA-Za-z]", "").length();
        /**
         * 50 points if the total is a round dollar amount with no cents
         */
        total = Double.parseDouble(receipt.getTotal());
        Double numAux = Math.floor(total);
        if(total-numAux == 0)
            points+=50;
        /**
         * 25 points if the total is a multiple of `0.25`
         */
        else if (total%0.25==0)
            points+=25;

        /**
         * 5 points for every two items on the receipt
         */
        items = receipt.getItems();
        points += Math.floor(items.size()/2)*5;

        /**
         * If the trimmed length of the item description is a multiple of 3, multiply the price by `0.2` and round
         * up to the nearest integer. The result is the number of points earned
         */
        for (Item item: items){
                if(Math.floor(item.getShortDescription().replace(" ","").length()%3)==0)
                points += Math.ceil(Double.parseDouble(item.getPrice())*0.2);
        }

        /**
         * 6 points if the day in the purchase date is odd
         */
        purchaseDate = receipt.getPurchaseDate();
        int day = Integer.parseInt(purchaseDate.substring(8));
        if(day%2!=0)
            points += 6;

        /**
         * 10 points if the time of purchase is after 2:00pm and before 4:00pm
         */
        purchaseTime = receipt.getPurchaseTime();
        int hr = Integer.parseInt(purchaseTime.substring(0,2));
        int min = Integer.parseInt(purchaseTime.substring(3));
        if(hr>=14 && hr<=16)
            if(hr == 16){
                if(min==0)
                    points += 10;
            } else points += 10;

        PointsRespose pointsRespose = new PointsRespose();
        pointsRespose.setPoints(points);
        return pointsRespose;
    }
}