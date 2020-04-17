package com.cooperativa.assembleia.web.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        Duration timeoutValue = Duration.ofMillis(15000);
        return builder.setConnectTimeout(timeoutValue).setReadTimeout(timeoutValue).build();
    }
}
