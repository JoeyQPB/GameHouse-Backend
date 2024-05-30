package com.joey.gamehousesugestionservice.exceptions;

public class SuggestionNotFoundException extends  RuntimeException{
    public SuggestionNotFoundException (String msg) {
        super(msg);
    }
}
