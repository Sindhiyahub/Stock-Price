package com.stockPrice.app.Service;

import com.stockPrice.app.entity.Order;
import com.stockPrice.app.entity.Order.OrderType;
import com.stockPrice.app.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockApi stockApi;

    public Mono<Order> placeOrder(String userId, String symbol, String orderType, int quantity) {
        return stockApi.fetchStockPrice(symbol)
                .flatMap(stock -> {
                    if (stock == null || stock.getCurrentPrice() == null) {
                        return Mono.error(new RuntimeException("Unable to fetch stock price for symbol: " + symbol));
                    }

                    // Log fetched stock details
                    System.out.println("Fetched stock price for " + symbol + ": " + stock.getCurrentPrice());

                    // Create and save a new order
                    Order order = new Order();
                    order.setUserId(userId);
                    order.setSymbol(symbol);
                    order.setOrderType(OrderType.valueOf(orderType.toUpperCase()));
                    order.setQuantity(quantity);
                    order.setPrice(stock.getCurrentPrice());
                    order.setTimestamp(new Date());

                    return Mono.just(orderRepository.save(order));
                })
                .onErrorResume(e -> {
                    System.err.println("Error while placing order: " + e.getMessage());
                    return Mono.error(new RuntimeException("Failed to place order. Reason: " + e.getMessage()));
                });
    }
}

