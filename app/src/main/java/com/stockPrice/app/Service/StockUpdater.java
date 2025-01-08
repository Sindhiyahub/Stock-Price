package com.stockPrice.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stockPrice.app.Repository.StockRepository;

@Component
public class StockUpdater {

    @Autowired
    private StockApi stockPriceService;

    @Autowired
    private StockRepository stockRepository;

    @Scheduled(fixedRate = 60000) // Fetch data every minute
    public void updateStockPrices() {
        // Example symbols to update
        String[] symbols = {"AAPL", "GOOGL", "AMZN"};

        for (String symbol : symbols) {
            stockPriceService.fetchStockPrice(symbol)
                    .subscribe(stock -> {
                        stockRepository.save(stock);
                        System.out.println("Updated stock: " + stock.getSymbol());
                    });
        }
    }
}
