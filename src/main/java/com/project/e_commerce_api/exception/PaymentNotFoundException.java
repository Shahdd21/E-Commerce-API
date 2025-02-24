package com.project.e_commerce_api.exception;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(String message){
        super(message);
    }
}
