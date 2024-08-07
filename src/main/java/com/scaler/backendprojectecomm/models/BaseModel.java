package com.scaler.backendprojectecomm.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass//attributes of this class will be columns for the inherited class tables
public class BaseModel {

    @Id//to indicate that it is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//to autoincrement id
    private long id;
    private Date createdAt;
    private Date updatedAt;
}
