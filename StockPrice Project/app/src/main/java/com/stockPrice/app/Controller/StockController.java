package com.stockPrice.app.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.stockPrice.app.Service.StockApi;
//import com.stockPrice.app.entity.Stock;
//
//import reactor.core.publisher.Mono;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class StockController {
//
//    @Autowired
//    private StockApi stockApiService;
//
//    @GetMapping("/api/stocks")
//    public Mono<ResponseEntity<Stock>> getStockPrice(@RequestParam String symbol) {
//        return stockApiService.fetchStockPrice(symbol)
//                .map(stock -> ResponseEntity.ok(stock))
//                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(null)));
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import com.stockPrice.app.Service.StockApi;
import com.stockPrice.app.entity.Stock;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StockController {

    @Autowired
    private StockApi stockApiService;

    @GetMapping("/api/stocks")
    public Mono<ResponseEntity<Stock>> getStockPrice(@RequestParam String symbol) {
        return stockApiService.fetchStockPrice(symbol)
                .map(stock -> ResponseEntity.ok()
                        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE")
                        .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, Authorization")
                        .body(stock))  // Return stock data in the response
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(null)))  // Handle error case
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));  // Handle empty response (stock not found)
    }
}
