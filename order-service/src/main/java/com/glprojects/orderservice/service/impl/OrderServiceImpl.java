package com.glprojects.orderservice.service.impl;

import com.glprojects.orderservice.dto.OrderRequestDTO;
import com.glprojects.orderservice.dto.OrderResponseDTO;
import com.glprojects.orderservice.entity.Order;
import com.glprojects.orderservice.exception.OrderNotFoundException;
import com.glprojects.orderservice.repository.OrderRepository;
import com.glprojects.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public OrderResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
    }

    @Override
    public OrderResponseDTO save(OrderRequestDTO dto) {
        Order order = toEntity(dto);
        Order saved = repository.save(order);
        return toResponse(saved);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderRequestDTO dto) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
        existing.setUserId(dto.getUserId());
        existing.setProduct(dto.getProduct());
        existing.setQuantity(dto.getQuantity());
        return toResponse(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
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
