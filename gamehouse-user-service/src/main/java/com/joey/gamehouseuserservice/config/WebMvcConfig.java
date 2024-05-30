package com.joey.gamehouseuserservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${url.api.gateway}")
    private String urlApiGateway;

    @Value("${url.frontend}")
    private String urlFrontEnd;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(urlApiGateway, urlFrontEnd)
                .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS");
    }
}