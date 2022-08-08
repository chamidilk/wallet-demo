package com.dilshan.wallet.exception;

public class InsufficientAmountException extends IllegalArgumentException{

    public InsufficientAmountException(String message){
        super(message);
    }
}
