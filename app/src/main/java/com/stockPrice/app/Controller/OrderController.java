package com.stockPrice.app.Controller;

import com.stockPrice.app.entity.Order;
import com.stockPrice.app.dto.OrderRequest;
import com.stockPrice.app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to buy a stock
    @PostMapping("/buy")
    @PreAuthorize("hasRole('USER')")
    public Mono<Order> buyStock(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(
                orderRequest.getUserId(),
                orderRequest.getSymbol(),
                "BUY",
                orderRequest.getQuantity()
        );
    }

    // Endpoint to sell a stock
    @PostMapping("/sell")
    public Mono<Order> sellStock(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(
                orderRequest.getUserId(),
                orderRequest.getSymbol(),
                "SELL",
                orderRequest.getQuantity()
        );
    }
}
