package com.joey.gamehouseuserservice.domain.records;

import org.springframework.security.core.userdetails.UserDetails;

public record LoginResponse (UserDetails user, String token) {
}
