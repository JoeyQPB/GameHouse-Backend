package com.joey.gamehousesugestionservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joey.gamehousesugestionservice.domain.records.SuggestionRequestDTO;
import com.joey.gamehousesugestionservice.service.RabbitMQService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suggestion")
public class PublisherController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    public void publisherSuggestion (@RequestBody @Valid SuggestionRequestDTO suggestionDTO) throws JsonProcessingException {
        this.rabbitMQService.sendSuggestion(suggestionDTO);
    }
}
