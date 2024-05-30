package com.joey.gamehouseuserservice.exceptions;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException (String msg) {
        super(msg);
    }
}
