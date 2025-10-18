package com.glprojects.orderservice.service;

import com.glprojects.orderservice.dto.OrderRequestDTO;
import com.glprojects.orderservice.dto.OrderResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    Mono<OrderResponseDTO> createOrder(OrderRequestDTO dto);

    Mono<OrderResponseDTO> getOrderById(String id);

    Flux<OrderResponseDTO> getAllOrders();

    Flux<OrderResponseDTO> getOrdersByUserId(String userId);

    Mono<OrderResponseDTO> updateOrder(String id, OrderRequestDTO dto);

    Mono<Void> deleteOrder(String id);
}
