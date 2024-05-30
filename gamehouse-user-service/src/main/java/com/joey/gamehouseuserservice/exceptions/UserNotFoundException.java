package com.joey.gamehouseuserservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String msg) {
        super(msg);
    }
}
