package com.scaler.backendprojectecomm.exceptions;

//custom exception class created for CategoryNotFound
public class CategoryNotFound extends Exception {
    private String category;
    public CategoryNotFound(String category,String message) {
        super(message);
        this.category = category;
    }
}
