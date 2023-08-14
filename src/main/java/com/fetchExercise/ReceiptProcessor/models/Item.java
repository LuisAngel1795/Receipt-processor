package com.fetchExercise.ReceiptProcessor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * Definition of entity Item
 */
@Entity
@Table(name="items")
public class Item implements Serializable {
    private static final long serialVersionUID = -7972529659762255854L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_item")
    private Integer id;
    @Column(name="short_description")
    @NotNull
    @NotBlank
    private String shortDescription;
    @NotNull
    @NotBlank
    private String price;

    @ManyToMany(mappedBy = "items")
    private List<Receipt> receipts;

    /**
     * METODOS GETTER AND SETTER
     * @return
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}