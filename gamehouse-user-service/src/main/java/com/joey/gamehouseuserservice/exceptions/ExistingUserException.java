package com.joey.gamehouseuserservice.exceptions;

public class ExistingUserException extends RuntimeException{
    public ExistingUserException (String msg) {
        super(msg);
    }
}
