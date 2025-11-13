package com.api.envio_rapido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${melhor-envio.base-url}")
    private String melhorEnvioBaseUrl = "https://sandbox.melhorenvio.com.br";

    @Value("${melhor-envio.token}")
    private String melhorEnvioToken;

    @Value("${melhor-envio.email-tecnico}")
    private String emailTecnico;

    @Bean(name = "melhorEnvioClient")
    public WebClient melhorEnvioClient() {
        String userAgentValue = "Envio Rapido API (" + emailTecnico + ")";
        return WebClient.builder()
                .baseUrl(melhorEnvioBaseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.USER_AGENT, userAgentValue)
                .build();

    }
}
