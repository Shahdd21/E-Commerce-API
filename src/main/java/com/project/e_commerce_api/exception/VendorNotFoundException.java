package com.project.e_commerce_api.exception;

public class VendorNotFoundException extends RuntimeException{

    public VendorNotFoundException(String message){
        super(message);
    }
}
