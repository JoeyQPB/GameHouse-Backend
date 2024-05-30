package com.joey.gamehouseuserservice.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String msg) {
        super(msg);
    }
}
