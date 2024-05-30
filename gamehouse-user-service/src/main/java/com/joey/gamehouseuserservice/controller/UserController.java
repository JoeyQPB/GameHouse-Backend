package com.joey.gamehouseuserservice.controller;

import com.joey.gamehouseuserservice.domain.User;
import com.joey.gamehouseuserservice.domain.records.CreateUserRequest;
import com.joey.gamehouseuserservice.domain.records.UpdateUserRequest;
import com.joey.gamehouseuserservice.service.AuthorizationService;
import com.joey.gamehouseuserservice.service.UserService;
import com.joey.gamehouseuserservice.service.enums.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll () {
        ServiceResponse<List<User>> serviceResponse = this.userService.findAll();
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @GetMapping("/qe={email}")
    public ResponseEntity<UserDetails> getByEmail (@PathVariable @Valid String email) {
        ServiceResponse<UserDetails> serviceResponse = this.userService.findByEmail(email);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById (@PathVariable @Valid Long id) {
        ServiceResponse<User> serviceResponse = this.userService.findById(id);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody @Valid UpdateUserRequest data,
                                     @PathVariable @Valid Long id) {
        ServiceResponse<User> serviceResponse = this.userService.update(data, id);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete (@PathVariable @Valid Long id) {
        ServiceResponse<Boolean> serviceResponse = this.userService.delete(id);
        return ResponseEntity.status(serviceResponse.httpStatus()).body(serviceResponse.body());
    }
}
