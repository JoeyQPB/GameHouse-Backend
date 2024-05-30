package com.joey.gamehousesendEmailservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ_Config {

    @Value("${application.queue.name}")
    private String queue_name;

    @Value("${application.exchange.name}")
    private String exchange_name;

    @Value("${application.route.name}")
    private String route_name;

    @Value("${application.dlq.queue.name}")
    private String DLQ_NAME;

    @Bean
    public Queue queue() {
        return new Queue(queue_name, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange_name);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(route_name);
    }

}
