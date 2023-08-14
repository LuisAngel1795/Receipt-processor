package com.fetchExercise.ReceiptProcessor.Dao;

import com.fetchExercise.ReceiptProcessor.models.Receipt;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 *Definition of Dao class to access the table receipts in H2 embedded database
 */
public interface IReceiptProcessDao extends CrudRepository<Receipt, UUID> {

}
