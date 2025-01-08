package com.stockPrice.app.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow credentials (cookies, authorization headers, etc.)
                .maxAge(3600); // Cache preflight response for 1 hour
    }
}

