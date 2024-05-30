package com.joey.gamehouseuserservice.repository;

import com.joey.gamehouseuserservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
