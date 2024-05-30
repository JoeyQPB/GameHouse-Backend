package com.joey.gamehouseuserservice.service;

import com.joey.gamehouseuserservice.domain.User;
import com.joey.gamehouseuserservice.domain.enums.Role;
import com.joey.gamehouseuserservice.domain.records.LoginAuthenticationDTO;
import com.joey.gamehouseuserservice.domain.records.LoginResponse;
import com.joey.gamehouseuserservice.domain.records.RegisterAuthenticationDTO;
import com.joey.gamehouseuserservice.exceptions.BadRequestException;
import com.joey.gamehouseuserservice.exceptions.ExistingUserException;
import com.joey.gamehouseuserservice.exceptions.MissingFieldsException;
import com.joey.gamehouseuserservice.exceptions.UserNotFoundException;
import com.joey.gamehouseuserservice.infra.security.TokenService;
import com.joey.gamehouseuserservice.producers.RegisterEmailProducer;
import com.joey.gamehouseuserservice.repository.IUserRepository;
import com.joey.gamehouseuserservice.service.enums.ServiceResponse;
import com.joey.gamehouseuserservice.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final AuthenticationManager authenticationManager;
    private final RegisterEmailProducer registerEmailProducer;
    private final IUserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService (AuthenticationManager authenticationManager,
                                  RegisterEmailProducer registerEmailProducer,
                                     IUserRepository userRepository,
                                     TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.registerEmailProducer = registerEmailProducer;
    }

    public ServiceResponse<User> registerAdm (RegisterAuthenticationDTO data) throws MissingFieldsException, ExistingUserException {
        if (UserUtils.isThereAnyFieldsNull(data))
            throw new MissingFieldsException("Missing field not null able!");

        if (!data.password().equals(data.confirmPassword()))
            throw new BadRequestException("Bad Register Request");

        if (this.userRepository.findByEmail(data.email()) != null)
            throw new ExistingUserException("The email is already register");

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());

        User userAdm = new User(data.email(), encodedPassword, Role.ADMIN);
        User userAdmCreated = this.userRepository.save(userAdm);

        LOGGER.info("Created User ADMIN ({}): name: {}", userAdmCreated.getId(), userAdmCreated.getEmail());
        registerEmailProducer.emailProducer(userAdmCreated);
        return new ServiceResponse<>(HttpStatus.CREATED, userAdmCreated);
    }

    public ServiceResponse<User> registerUser (RegisterAuthenticationDTO data) throws MissingFieldsException, ExistingUserException {
        if (UserUtils.isThereAnyFieldsNull(data))
            throw new MissingFieldsException("Missing field not null able!");

        if (!data.password().equals(data.confirmPassword()))
            throw new BadRequestException("Bad Register Request");

        if (this.userRepository.findByEmail(data.email()) != null)
            throw new ExistingUserException("The email is already register");

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());

        User user = new User(data.email(), encodedPassword, Role.USER);
        User userCreated = this.userRepository.save(user);

        LOGGER.info("Created User ({}): name: {}", userCreated.getId(), userCreated.getEmail());
        registerEmailProducer.emailProducer(userCreated);
        return new ServiceResponse<>(HttpStatus.CREATED, userCreated);
    }

    public ServiceResponse<LoginResponse> login (LoginAuthenticationDTO data) throws MissingFieldsException, UserNotFoundException {
        if (UserUtils.isThereAnyFieldsNull(data))
            throw new MissingFieldsException("Missing field not null able!");

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        var token = this.tokenService.generateToken(user);

        LoginResponse loginResponse = new LoginResponse(user, token);

        LOGGER.info("Login with: {} ", user);
        return new ServiceResponse<>(HttpStatus.OK, loginResponse);
    }

    public ServiceResponse<Boolean> verifyToken (HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        String token = authHeader.replace("Bearer ", "");

        var email = this.tokenService.isValidToken(token);
        boolean isTokenValid = email != null;

        LOGGER.info("Verify Token - Valid: {}", isTokenValid);
        return new ServiceResponse<>(HttpStatus.OK, isTokenValid);
    }
}
