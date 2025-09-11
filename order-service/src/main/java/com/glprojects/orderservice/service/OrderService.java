package com.glprojects.orderservice.service;

import com.glprojects.orderservice.dto.OrderRequestDTO;
import com.glprojects.orderservice.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> getAll();
    OrderResponseDTO getById(Long id);
    OrderResponseDTO save(OrderRequestDTO dto);
    OrderResponseDTO update(Long id, OrderRequestDTO dto);
    void delete(Long id);
}
