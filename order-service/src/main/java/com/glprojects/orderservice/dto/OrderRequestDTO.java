package com.glprojects.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "Product is required")
    private String product;

    @NotNull(message = "Quantity is required")
    private Integer quantity;
}
