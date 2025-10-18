package com.glprojects.orderservice.service.impl;

import com.glprojects.orderservice.client.UserClient;
import com.glprojects.orderservice.dto.OrderRequestDTO;
import com.glprojects.orderservice.dto.OrderResponseDTO;
import com.glprojects.orderservice.entity.Order;
import com.glprojects.orderservice.exception.OrderNotFoundException;
import com.glprojects.orderservice.repository.OrderRepository;
import com.glprojects.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserClient userClient;

    public OrderServiceImpl(OrderRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    @Override
    public Flux<OrderResponseDTO> getAllOrders() {
        return repository.findAll()
                .map(this::toResponse);
    }

    @Override
    public Flux<OrderResponseDTO> getOrdersByUserId(String userId) {
        return repository.findByUserId(userId)
                .map(this::toResponse);
    }

    @Override
    public Mono<OrderResponseDTO> getOrderById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("Order with id " + id + " not found")))
                .map(this::toResponse);
    }

    @Override
    public Mono<OrderResponseDTO> createOrder(OrderRequestDTO dto) {
        return userClient.getUserById(String.valueOf(dto.getUserId()))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found: " + dto.getUserId())))
                .flatMap(user -> {
                    Order order = toEntity(dto);
                    return repository.save(order)
                            .map(this::toResponse);
                });
    }

    @Override
    public Mono<OrderResponseDTO> updateOrder(String id, OrderRequestDTO dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("Order with id " + id + " not found")))
                .flatMap(existing ->
                        userClient.getUserById(String.valueOf(dto.getUserId()))
                                .switchIfEmpty(Mono.error(new RuntimeException("User not found: " + dto.getUserId())))
                                .flatMap(user -> {
                                    existing.setProduct(dto.getProduct());
                                    existing.setQuantity(dto.getQuantity());
                                    existing.setUserId(dto.getUserId());
                                    return repository.save(existing)
                                            .map(this::toResponse);
                                })
                );
    }

    @Override
    public Mono<Void> deleteOrder(String id) {
        return repository.deleteById(id);
    }

    private Order toEntity(OrderRequestDTO dto) {
        return Order.builder()
                .userId(dto.getUserId())
                .product(dto.getProduct())
                .quantity(dto.getQuantity())
                .build();
    }

    private OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setProduct(order.getProduct());
        dto.setQuantity(order.getQuantity());
        return dto;
    }
}
