package com.example.flipcommerce.Exception;

public class ProductNotExistsException extends RuntimeException{
    public ProductNotExistsException(String message){
        super(message);
    }
}
