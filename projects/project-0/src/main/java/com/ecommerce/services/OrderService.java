package com.ecommerce.services;

import com.ecommerce.models.Order;
import com.ecommerce.repos.OrderDAO;

import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getOrdersForUser(int userId) {
        return orderDAO.getOrdersByUserId(userId);
    }
}
