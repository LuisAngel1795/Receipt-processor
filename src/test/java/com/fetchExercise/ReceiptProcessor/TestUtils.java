package com.fetchExercise.ReceiptProcessor;

import com.fetchExercise.ReceiptProcessor.models.Item;
import com.fetchExercise.ReceiptProcessor.models.Receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static List<Item> loadItems(){
        Item item = new Item();
        List<Item> items = new ArrayList<>();
        item.setPrice("6.49");
        item.setId(1);
        item.setShortDescription("Mountain Dew 12PK");
        items.add(item);
        return items;
    }

    public static Receipt createReceipt(){
        Receipt receipt = new Receipt();
        receipt.setId(UUID.randomUUID());
        receipt.setItems(loadItems());
        receipt.setPurchaseDate("2022-01-01");
        receipt.setPurchaseTime("13:01");
        receipt.setRetailer("Target");
        receipt.setTotal("35.35");
        return receipt;
    }
}
