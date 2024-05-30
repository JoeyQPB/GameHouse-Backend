package com.joey.gamehousesugestionservice.controller;

import com.joey.gamehousesugestionservice.domain.Suggestion;
import com.joey.gamehousesugestionservice.repository.SuggestionRepository;
import com.joey.gamehousesugestionservice.service.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestionRepository suggestionRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(SuggestionController.class);

    @GetMapping("/getAllByAuthor/qe={email}")
    public ResponseEntity<List<Suggestion>> getAllByEmail (@PathVariable String email) {
        LOGGER.info("[:] Fetching all suggestions by email: {}", email);
        return ResponseEntity.status(HttpStatus.OK).body(this.suggestionRepository.findAllByEmail(email));
    }
}
