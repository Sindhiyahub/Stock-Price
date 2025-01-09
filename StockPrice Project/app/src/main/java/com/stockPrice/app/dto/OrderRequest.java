package com.stockPrice.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderRequest {

    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @NotBlank(message = "Stock symbol cannot be blank")
    private String symbol;

    @NotBlank(message = "Order type cannot be blank")
    private String orderType; // BUY or SELL

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
