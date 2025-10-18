package com.glprojects.orderservice.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String id;

    @NotNull(message = "UserId is required")
    private Long userId;  // relación con user-service

    @NotNull(message = "Product is required")
    private String product;

    @NotNull(message = "Quantity is required")
    private Integer quantity;
}
