package com.joey.gamehouseuserservice.domain.records;

public record UpdateUserRequest (String oldEmail, String newEmail) {
}
