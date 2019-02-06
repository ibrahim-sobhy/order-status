package com.alten.training.order.status.api;

import com.alten.training.order.status.model.Order;
import com.alten.training.order.status.repo.OrderRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/status")
public class OrderStatusApi {

    private OrderRepo repo;

    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<Order>> find(@PathVariable("orderId") Long orderId) {
        return Mono.just(
                repo.findById(orderId)
                        .map(ResponseEntity::ok)
                        .orElse(notFound().build())
        );
    }

}
