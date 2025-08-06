package com.sumanta.HackFest.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
