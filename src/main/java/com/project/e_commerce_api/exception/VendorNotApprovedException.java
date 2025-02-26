package com.project.e_commerce_api.exception;

public class VendorNotApprovedException extends RuntimeException{
    public VendorNotApprovedException(String message){
        super(message);
    }
}
