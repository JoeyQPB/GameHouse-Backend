package com.joey.gamehouseuserservice.controller;

import com.joey.gamehouseuserservice.domain.User;
import com.joey.gamehouseuserservice.domain.records.LoginAuthenticationDTO;
import com.joey.gamehouseuserservice.domain.records.LoginResponse;
import com.joey.gamehouseuserservice.domain.records.RegisterAuthenticationDTO;
import com.joey.gamehouseuserservice.service.AuthenticationService;
import com.joey.gamehouseuserservice.service.enums.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register_adm")
    public ResponseEntity<User> registerAdm (@RequestBody @Valid RegisterAuthenticationDTO data) {
        ServiceResponse<User> serviceResponse = this.authenticationService.registerAdm(data);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @PostMapping("/register_user")
    public ResponseEntity<User> registerUser (@RequestBody @Valid RegisterAuthenticationDTO data) {
        ServiceResponse<User> serviceResponse = this.authenticationService.registerUser(data);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody @Valid LoginAuthenticationDTO data) {
        ServiceResponse<LoginResponse> serviceResponse = this.authenticationService.login(data);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @PostMapping("/verify_token")
    public ResponseEntity<Boolean> verifyToken (HttpServletRequest request) {
        ServiceResponse<Boolean> serviceResponse = this.authenticationService.verifyToken(request);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

}
