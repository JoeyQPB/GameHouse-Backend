package com.joey.gamehouseuserservice.service;

import com.joey.gamehouseuserservice.exceptions.UserNotFoundException;
import com.joey.gamehouseuserservice.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);

    @Override
    public UserDetails loadUserByUsername(String email) {
        LOGGER.info("loadUserByUsername : {} ", email);

        UserDetails userDetails = this.userRepository.findByEmail(email);

        if (userDetails == null) {
            LOGGER.info("User not found for email: {}", email);
            throw new UserNotFoundException("User not found for email: " + email);
        }

        return userDetails;
    }
}
