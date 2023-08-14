package com.fetchExercise.ReceiptProcessor.Dao;

import com.fetchExercise.ReceiptProcessor.models.Item;
import com.fetchExercise.ReceiptProcessor.models.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *Definition of Dao class to access the table items in H2 embedded database
 */
public interface IItemDao extends CrudRepository<Item, Integer> {

    /**
     * Custom Query is used create a query that are not provided by the spring Data JPA Repository
     * In this case it is used to get the item by its short_description
     * @param shortDescription
     * @return
     */
   @Query(value = "SELECT * FROM items WHERE short_description = ?1", nativeQuery = true)
    Item findItemByShortDescription(String shortDescription);

}
