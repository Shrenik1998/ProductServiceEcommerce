package com.scaler.backendprojectecomm.exceptions;

public class ProductNotFound extends Exception{
    private long productId;
    public ProductNotFound(long id,String message){
        super(message);
        this.productId=id;
    }
}
