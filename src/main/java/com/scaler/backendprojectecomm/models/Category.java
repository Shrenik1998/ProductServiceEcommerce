package com.scaler.backendprojectecomm.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity//Table of this entity will be created by JPA
public class Category extends BaseModel{
    private String name;
    private String description;
}
