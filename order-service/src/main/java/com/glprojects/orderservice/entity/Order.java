package com.glprojects.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "UserId is required")
    private Long userId;  // relación con user-service

    @NotNull(message = "Product is required")
    private String product;

    @NotNull(message = "Quantity is required")
    private Integer quantity;
}
