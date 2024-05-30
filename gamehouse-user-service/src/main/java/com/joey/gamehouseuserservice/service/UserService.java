package com.joey.gamehouseuserservice.service;

import com.joey.gamehouseuserservice.domain.User;
import com.joey.gamehouseuserservice.domain.records.UpdateUserRequest;
import com.joey.gamehouseuserservice.exceptions.MissingFieldsException;
import com.joey.gamehouseuserservice.exceptions.UserNotFoundException;
import com.joey.gamehouseuserservice.repository.IUserRepository;
import com.joey.gamehouseuserservice.service.enums.ServiceResponse;
import com.joey.gamehouseuserservice.utils.UserUtils;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final AuthorizationService authorizationService;
    private final IUserRepository userRepository;

    @Autowired
    public UserService (IUserRepository userRepository,
                        AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
    }

    public ServiceResponse<List<User>> findAll () {
        LOGGER.info("Finding all users");
        return new ServiceResponse<>(HttpStatus.OK, this.userRepository.findAll());
    }

    public ServiceResponse<UserDetails> findByEmail (String email) throws UserNotFoundException {
        UserDetails user = this.authorizationService.loadUserByUsername(email);

        if (user == null) throw  new UserNotFoundException("User not found for email: " + email);

        LOGGER.info("Finding user by email: {} ", email);
        return new ServiceResponse<>(HttpStatus.OK, user);
    }

    public ServiceResponse<User> findById (Long id) throws UserNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));

        LOGGER.info("Finding user by id: {} ", id);
        return new ServiceResponse<>(HttpStatus.OK, user);
    }

    public ServiceResponse<User> update (UpdateUserRequest data, Long id) throws UserNotFoundException {
        if (UserUtils.isThereAnyFieldsNull(data))
            throw new MissingFieldsException("Missing field not null able!");

        if (UserUtils.isEmailsEquals(data.oldEmail(), data.newEmail()))
            throw new MissingFieldsException("You need update to a different email!");

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user for id: " + id));

        user.setEmail(data.newEmail());
        User userUpdated = this.userRepository.save(user);

        LOGGER.info("Updated user {}: new email {}", id, userUpdated.getEmail());
        return new ServiceResponse<>(HttpStatus.OK, userUpdated);
    }

    public ServiceResponse<Boolean> delete (Long id) {
        User optionalUser = this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user for id: " + id));

        this.userRepository.delete(optionalUser);
        LOGGER.info("Deleted user by id: {} ", id);
        return new ServiceResponse<>(HttpStatus.OK, true);
    }

}
