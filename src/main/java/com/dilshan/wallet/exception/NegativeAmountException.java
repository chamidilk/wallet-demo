package com.dilshan.wallet.exception;

public class NegativeAmountException extends IllegalArgumentException{

    public NegativeAmountException(String message){
        super(message);
    }
}
