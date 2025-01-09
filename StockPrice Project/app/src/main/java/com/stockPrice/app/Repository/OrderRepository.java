package com.stockPrice.app.Repository;

import com.stockPrice.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query to find orders by stock symbol
    List<Order> findBySymbol(String symbol);

    // Custom query to find orders by type (e.g., BUY or SELL)
    List<Order> findByOrderType(String orderType);
    
    List<Order> findByUserId(String userId);
}
