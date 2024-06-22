package com.joey.gamehousesugestionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${url.api.gateway:url}")
    private String urlApiGateway;

    @Value("${url.frontend:url}")
    private String urlFrontEnd;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(urlApiGateway, urlFrontEnd)
                .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS");
    }
}
