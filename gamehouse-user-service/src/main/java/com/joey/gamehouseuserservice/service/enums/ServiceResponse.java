package com.joey.gamehouseuserservice.service.enums;

import org.springframework.http.HttpStatus;

public record ServiceResponse<E> (HttpStatus httpStatus, E body) {
}
