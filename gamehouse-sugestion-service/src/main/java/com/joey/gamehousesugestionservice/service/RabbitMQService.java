package com.joey.gamehousesugestionservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joey.gamehousesugestionservice.domain.Suggestion;
import com.joey.gamehousesugestionservice.domain.records.SuggestionRequestDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    @Value("${application.route.name}")
    private String route_name;

    @Value("${application.exchange.name}")
    private String exchange_name;

    private final RabbitTemplate rabbitTemplate;
    private final SuggestionService suggestionService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate,
                            SuggestionService suggestionService) {
        this.rabbitTemplate = rabbitTemplate;
        this.suggestionService = suggestionService;
    }

    @Transactional
    public void sendSuggestion(SuggestionRequestDTO suggestionDTO) throws JsonProcessingException {
        Suggestion suggestion = this.suggestionService.create(suggestionDTO);
        String suggestionSerial = objectMapper.writeValueAsString(suggestion);

        rabbitTemplate.convertAndSend(exchange_name, route_name, suggestionSerial);
        LOGGER.info("[:] Sent Suggestion: {}", suggestionSerial);
        LOGGER.info("[>] To exchange: {}, through route: {}", exchange_name, route_name);
    }
}
