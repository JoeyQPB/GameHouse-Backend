package com.joey.gamehousesendEmailservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joey.gamehousesendEmailservice.model.SuggestionModel;
import com.joey.gamehousesendEmailservice.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSuggestionProducer {

    @Autowired
    private RabbitMQService rabbitMQService;

    public void sendUpdate (SuggestionModel suggestionModel) throws JsonProcessingException {
        this.rabbitMQService.sendUpdate(suggestionModel);
    }
}
