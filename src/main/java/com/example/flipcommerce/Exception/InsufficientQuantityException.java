package com.example.flipcommerce.Exception;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String message){
        super(message);
    }
}
