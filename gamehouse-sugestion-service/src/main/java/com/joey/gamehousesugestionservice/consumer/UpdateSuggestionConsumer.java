package com.joey.gamehousesugestionservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joey.gamehousesugestionservice.domain.Suggestion;
import com.joey.gamehousesugestionservice.domain.records.SuggestionConsumerDTO;
import com.joey.gamehousesugestionservice.service.RabbitMQService;
import com.joey.gamehousesugestionservice.service.SuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${application.queue.updateSuggestion}")
public class UpdateSuggestionConsumer {

    @Value("${application.queue.updateSuggestion}")
    private String queueName;

    @Autowired
    private SuggestionService suggestionService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);

    @RabbitHandler
    public void consumer(@Payload String suggestionData) throws JsonProcessingException {
        SuggestionConsumerDTO suggestionDto = objectMapper.readValue(suggestionData, SuggestionConsumerDTO.class);
        LOGGER.info("Consuming Message from [{}]: {}", queueName, suggestionData);

        Suggestion suggestion = this.suggestionService.update(suggestionDto.id(), suggestionDto.consumed());
        LOGGER.info("Consumed Message with successful: {}",  suggestion);
    }
}
