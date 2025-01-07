package com.stockPrice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(exclude = {
	    org.springframework.ai.autoconfigure.vectorstore.redis.RedisVectorStoreAutoConfiguration.class
	})

public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
