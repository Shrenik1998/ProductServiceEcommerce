package com.scaler.backendprojectecomm.exceptions;

//custom exception class created for ProductNotFound
public class ProductNotFound extends Exception{
    private long productId;
    public ProductNotFound(long id,String message){
        super(message);
        this.productId=id;
    }
}