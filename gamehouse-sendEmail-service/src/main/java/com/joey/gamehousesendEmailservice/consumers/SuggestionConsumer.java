package com.joey.gamehousesendEmailservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joey.gamehousesendEmailservice.exceptions.EmailServiceException;
import com.joey.gamehousesendEmailservice.model.SuggestionModel;
import com.joey.gamehousesendEmailservice.producer.UpdateSuggestionProducer;
import com.joey.gamehousesendEmailservice.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${application.queue.name}")
public class SuggestionConsumer {

    @Value("${application.email}")
    private String email;

    @Value("${application.queue.name}")
    private String queueName;

    private final UpdateSuggestionProducer producer;
    private final EmailSenderService emailSenderService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(SuggestionConsumer.class);

    @Autowired
    public SuggestionConsumer (UpdateSuggestionProducer producer,
                               EmailSenderService emailSender) {
        this.producer = producer;
        this.emailSenderService = emailSender;
    }


    @RabbitHandler
    public void consumer(@Payload String suggestion) throws JsonProcessingException, EmailServiceException {
        SuggestionModel suggestionModel = objectMapper.readValue(suggestion, SuggestionModel.class);
        LOGGER.info("Consuming Message from [{}]: {}", queueName, suggestionModel);

        this.emailSenderService.sendEmail(
                email, "Suggestion new Game",
                "Suggestion: " + suggestionModel.getSuggestion() + "\n From: " + suggestionModel.getEmail());
        LOGGER.info("Email sent to: {}", email);

        suggestionModel.setConsumed(true);
        this.producer.sendUpdate(suggestionModel);
    }

}
