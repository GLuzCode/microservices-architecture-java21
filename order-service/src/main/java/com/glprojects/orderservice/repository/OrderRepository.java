package com.glprojects.orderservice.repository;

import com.glprojects.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findByUserId(String userId);
}
