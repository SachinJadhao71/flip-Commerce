package com.example.flipcommerce.Exception;

public class OrderIdNotValidException extends RuntimeException{
    public OrderIdNotValidException(String message){
        super(message);
    }
}
