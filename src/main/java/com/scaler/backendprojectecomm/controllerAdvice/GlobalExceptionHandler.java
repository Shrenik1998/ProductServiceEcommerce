package com.scaler.backendprojectecomm.controllerAdvice;

import com.scaler.backendprojectecomm.dtos.Exceptiondto;
import com.scaler.backendprojectecomm.exceptions.CategoryNotFound;
import com.scaler.backendprojectecomm.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice()
public class GlobalExceptionHandler {

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
