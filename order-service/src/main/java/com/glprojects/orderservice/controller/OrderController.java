package com.glprojects.orderservice.controller;

import com.glprojects.orderservice.dto.OrderRequestDTO;
import com.glprojects.orderservice.dto.OrderResponseDTO;
import com.glprojects.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // Obtener todas las órdenes
    @GetMapping
    public Flux<OrderResponseDTO> getAllOrders() {
        return service.getAllOrders();
    }

    // Obtener una orden por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<OrderResponseDTO>> getById(@PathVariable String id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Obtener órdenes por userId
    @GetMapping("/user/{userId}")
    public Flux<OrderResponseDTO> getByUserId(@PathVariable String userId) {
        return service.getOrdersByUserId(userId);
    }

    // Crear nueva orden
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO dto) {
        return service.createOrder(dto);
    }

    // Actualizar orden
    @PutMapping("/{id}")
    public Mono<ResponseEntity<OrderResponseDTO>> updateOrder(@PathVariable String id,
                                                              @Valid @RequestBody OrderRequestDTO dto) {
        return service.updateOrder(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Eliminar orden
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteOrder(@PathVariable String id) {
        return service.deleteOrder(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}