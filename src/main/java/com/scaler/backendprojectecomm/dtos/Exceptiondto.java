package com.scaler.backendprojectecomm.dtos;

import lombok.Getter;
import lombok.Setter;


//Object of this class will be returned when an exception is thrown
@Getter
@Setter
public class Exceptiondto {
    String message;
    String Solution;
}
