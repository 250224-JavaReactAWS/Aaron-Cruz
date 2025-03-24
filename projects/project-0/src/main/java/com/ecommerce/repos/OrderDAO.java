package com.ecommerce.repos;

import com.ecommerce.models.Order;

import java.util.List;

public interface OrderDAO extends GeneralDAO<Order> {

    List<Order> getOrdersByUserId(int userId);

}
