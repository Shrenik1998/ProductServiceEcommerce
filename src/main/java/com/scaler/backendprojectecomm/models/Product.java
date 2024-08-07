package com.scaler.backendprojectecomm.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity//Table of this entity will be created by JPA
public class Product extends BaseModel {
    private String title;
    private String description;
    private int qty;
    private double price;
    @ManyToOne(cascade = CascadeType.PERSIST)//when product is saved, along with product category is also saved
    private Category category;
}
