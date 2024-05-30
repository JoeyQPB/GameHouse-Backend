package com.joey.gamehousesendEmailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joey.gamehousesendEmailservice.model.SuggestionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    @Value("${application.route.updateSuggestion}")
    private String route_name;

    @Value("${application.exchange.name}")
    private String exchange_name;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);

    public void sendUpdate (SuggestionModel suggestionModel) throws JsonProcessingException {
        String suggestionSerial = objectMapper.writeValueAsString(suggestionModel);

        rabbitTemplate.convertAndSend(exchange_name, route_name, suggestionSerial);
        LOGGER.info("[:] Sent Suggestion: {}", suggestionSerial);
        LOGGER.info("[>] To exchange: {}, through route: {}", exchange_name, route_name);
    }
}
