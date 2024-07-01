package com.scaler.backendprojectecomm.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private long id;
    private String title;
    private String description;
    private int qty;
    private double price;
    private Category category;
}
