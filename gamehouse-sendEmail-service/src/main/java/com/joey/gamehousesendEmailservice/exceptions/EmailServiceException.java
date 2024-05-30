package com.joey.gamehousesendEmailservice.exceptions;

import java.io.Serial;

public class EmailServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailServiceException(String msg, String errorMsg) {
        super(msg + " - " + errorMsg);
    }
}
