package com.project.e_commerce_api.exception;

public class InvalidPaymentMethodException extends RuntimeException{

    public InvalidPaymentMethodException(String message){
        super(message);
    }
}
