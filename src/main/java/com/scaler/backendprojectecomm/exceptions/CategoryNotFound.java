package com.scaler.backendprojectecomm.exceptions;


public class CategoryNotFound extends Exception {
    private String category;
    public CategoryNotFound(String categoryy,String message) {
        super(message);
        this.category = categoryy;
    }
}
