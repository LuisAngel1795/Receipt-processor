package com.fetchExercise.ReceiptProcessor.models;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

/**
 * Definition of entity Receipt
 */
@Entity
@Table(name="receipts")
public class Receipt {
       @Id
       @GeneratedValue(generator = "UUID")
       @GenericGenerator(
               name = "UUID",
               strategy = "org.hibernate.id.UUIDGenerator"
       )
       @Column(name="id_receipt")
       private UUID id;
       @NotNull
       @NotBlank
       private String retailer;
       @Column(name="purchase_date")
       @NotNull
       @NotBlank
       private String purchaseDate;
       @Column(name="purchase_time")
       @NotNull
       @NotBlank
       private String purchaseTime;

       @ManyToMany
       @JoinTable(
               name = "recibo_item",
               joinColumns = @JoinColumn(name = "recibo_id"),
               inverseJoinColumns = @JoinColumn(name = "item_id")
       )
       @NotNull
       @Valid
       private List<Item> items;

       @NotNull
       @NotBlank
       private String total;

       public UUID getId() {
              return id;
       }

       public void setId(UUID id) {
              this.id = id;
       }

       public String getRetailer() {
              return retailer;
       }

       public void setRetailer(String retailer) {
              this.retailer = retailer;
       }

       public String getPurchaseDate() {
              return purchaseDate;
       }

       public void setPurchaseDate(String purchaseDate) {
              this.purchaseDate = purchaseDate;
       }

       public String getPurchaseTime() {
              return purchaseTime;
       }

       public void setPurchaseTime(String purchaseTime) {
              this.purchaseTime = purchaseTime;
       }

       public List<Item> getItems() {
              return items;
       }

       public void setItems(List<Item> items) {
              this.items = items;
       }

       public String getTotal() {
              return total;
       }

       public void setTotal(String total) {
              this.total = total;
       }
}