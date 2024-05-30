package com.joey.gamehouseuserservice.infra.security;

import com.joey.gamehouseuserservice.exceptions.UserNotFoundException;
import com.joey.gamehouseuserservice.repository.IUserRepository;
import com.joey.gamehouseuserservice.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = recoverToken(request);
            if (token != null) {
                var email = this.tokenService.validateToken(token);
                UserDetails user = this.userRepository.findByEmail(email);

                if (user == null) throw new UserNotFoundException("User not for email: " + email);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOGGER.info("Error doing filter: " + e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
