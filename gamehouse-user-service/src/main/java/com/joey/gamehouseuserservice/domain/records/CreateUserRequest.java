package com.joey.gamehouseuserservice.domain.records;

public record CreateUserRequest (String email, String encodedPassword) {
}
