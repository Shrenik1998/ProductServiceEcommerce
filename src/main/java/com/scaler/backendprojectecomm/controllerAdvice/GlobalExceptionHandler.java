package com.scaler.backendprojectecomm.controllerAdvice;

import com.scaler.backendprojectecomm.dtos.Exceptiondto;
import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//Product Service throws an exception which is caught by controller
// throws an exception which is caught by controller advice
@ControllerAdvice()
public class GlobalExceptionHandler {

    //product service will throw an exception object "productNotFound" which is caught by controller
    //this funciton will catch the "productNotFound" object throw by the controller and return "Exceptiondto" object
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Exceptiondto> handleProductNotFound(ProductNotFound productNotFound){
        Exceptiondto exceptiondto = new Exceptiondto();
        exceptiondto.setMessage(productNotFound.getMessage());
        exceptiondto.setSolution("Please Enter a valid Product ID");

        ResponseEntity<Exceptiondto> response = new ResponseEntity<>(
                exceptiondto, HttpStatus.NOT_FOUND
        );

        return response;
    }

    //product service will throw an exception object "CategoryNotFound" which is caught by controller
    //this funciton will catch the "CategoryNotFound" object throw by the controller and return "Exceptiondto" object
    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<Exceptiondto> handleCatogeryNotFoundException(CategoryNotFound exception){
        Exceptiondto exceptiondto = new Exceptiondto();
        exceptiondto.setMessage(exception.getMessage());
        exceptiondto.setSolution("Please Enter a valid category");

        ResponseEntity<Exceptiondto> response = new ResponseEntity<>(
                exceptiondto, HttpStatus.NOT_FOUND
        );

        return response;
    }

}
