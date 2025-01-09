package com.stockPrice.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stockPrice.app.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    // Custom Query Methods (if needed)
    Stock findBySymbol(String symbol); // Find stock by symbol
}
