package com.example.flipcommerce.Exception;

public class InvalidCardException extends RuntimeException{
    public InvalidCardException(String message){
        super(message);
    }
}
