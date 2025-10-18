package com.glprojects.orderservice.client;

import com.glprojects.orderservice.dto.UserResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder builder) {
        // Puedes parametrizar la URL en el Config Server si deseas
        this.webClient = builder.baseUrl("http://localhost:8081/users").build();
    }

    public Mono<UserResponseDTO> getUserById(String userId) {
        return webClient.get()
                .uri("/{id}", userId)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .onErrorResume(e -> {
                    System.out.println("Error calling user-service: " + e.getMessage());
                    return Mono.empty(); // Retorna vacío si falla
                });
    }
}

