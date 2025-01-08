
package com.stockPrice.app.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockPrice.app.entity.Stock;

import reactor.core.publisher.Mono;

@Service
public class StockApi {

    private final WebClient webClient;

    @Value("${alpha.vantage.api.url}")
    private String apiUrl;

    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    public StockApi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<Stock> fetchStockPrice(String symbol) {
        // Ensure full URL is constructed correctly
        String uri = String.format("%s?function=TIME_SERIES_INTRADAY&symbol=%s&interval=1min&apikey=%s", apiUrl, symbol, apiKey);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("API Response: " + response))

                .doOnError(e -> System.err.println("Error fetching stock price: " + e.getMessage()))
                .map(response -> mapToStock(response, symbol)) // Pass symbol dynamically
                .onErrorResume(e -> {
                    System.err.println("Failed to fetch stock data: " + e.getMessage());
                    return Mono.empty(); // Return an empty Mono to prevent crashing
                });
    }

    private Stock mapToStock(String response, String symbol) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode timeSeries = root.path("Time Series (1min)");

            if (timeSeries.isMissingNode() || timeSeries.isEmpty()) {
                throw new RuntimeException("Time Series data not found in API response");
            }

            // Extract the latest timestamp and corresponding data
            String latestTime = timeSeries.fieldNames().next();
            JsonNode latestData = timeSeries.path(latestTime);

            // Map the data to a Stock object
            Stock stock = new Stock();
            stock.setSymbol(symbol); // Dynamically set the symbol
            stock.setCurrentPrice(latestData.path("1. open").asDouble());
            stock.setLastUpdated(new Date());
            return stock;

        } catch (Exception e) {
            throw new RuntimeException("Error parsing stock data: " + e.getMessage(), e);
        }
    }
} 