package com.project.e_commerce_api.exception;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException(String message){
        super(message);
    }
}
