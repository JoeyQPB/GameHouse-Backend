package com.joey.gamehousesugestionservice.exceptions.records;

import org.springframework.http.HttpStatus;

public record ErrorResponse<E> (HttpStatus httpStatus, E body) {
}
