package com.joey.gamehousesendEmailservice.exceptions.recrods;

import org.springframework.http.HttpStatus;

public record ErrorResponse<E> (HttpStatus httpStatus, E body) {
}
