package com.glprojects.orderservice.dto;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private String product;
    private Integer quantity;
}
