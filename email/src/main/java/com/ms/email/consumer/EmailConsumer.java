package com.ms.email.consumer;

import com.ms.email.dtos.EmailDto;
import com.ms.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = "${application.queue.name}")
    public void listenEmailQueue (@Payload EmailDto emailDto) {
        String message = this.emailService.sendEmail(emailDto);

        if (message.contains("!!!")) LOGGER.error(message);
        else LOGGER.info(message);
    }
}
