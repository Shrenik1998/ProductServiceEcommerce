package com.scaler.backendprojectecomm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//This class will store the data recieved from fakestore api
public class FakeStoreDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
