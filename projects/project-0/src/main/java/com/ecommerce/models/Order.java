package com.ecommerce.models;

import java.util.Date;

public class Order {

    private int orderId;

    private int userId;

    private float totalPrice;

    private Status status;

    private Date createdAt;

    public Order(int orderId, int userId, float totalPrice, Status status, Date createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}
