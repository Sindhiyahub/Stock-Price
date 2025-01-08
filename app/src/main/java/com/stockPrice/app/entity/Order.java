package com.stockPrice.app.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // The ID of the user placing the order

    @Column(nullable = false)
    private String symbol; // The stock being bought or sold

    public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType; // BUY or SELL

    @Column(nullable = false)
    private int quantity; // Number of stocks

    @Column(nullable = false)
    private double price; // Price at which the stock is bought or sold

    @Column(nullable = false)
    private Date timestamp; // When the order was placed

    // Getters and Setters
    public enum OrderType {
        BUY, SELL
    } 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}


