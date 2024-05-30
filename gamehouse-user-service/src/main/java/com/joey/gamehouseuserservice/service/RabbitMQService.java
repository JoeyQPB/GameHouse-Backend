package com.joey.gamehouseuserservice.service;

import com.joey.gamehouseuserservice.domain.records.UserEmailProducerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    @Value("${application.route.register.name}")
    private String route_name;

    @Value("${application.exchange.register.name}")
    private String exchange_name;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMQService.class);

    public void sendRegisterEmail(UserEmailProducerDTO dto) {
        rabbitTemplate.convertAndSend(exchange_name, route_name, dto);
        LOGGER.info("[:] Producer sendRegisterEmail: {}", dto);
        LOGGER.info("[>] To exchange: {}, through route: {}", exchange_name, route_name);
    }
}
