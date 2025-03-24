package com.ecommerce.controllers;

import com.ecommerce.dtos.response.ErrorMessage;
import com.ecommerce.models.Order;
import com.ecommerce.services.OrderService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void fetcherUserOrdersHandler(Context ctx) {
        Integer userId = ctx.sessionAttribute("user_id");

        if (userId == null) {
            ctx.status( 401);
            ctx.json(new ErrorMessage("You must be logged to view your orders"));
            return;
        }

        List<Order> returnedOrderList = orderService.getOrdersForUser(userId);

        ctx.status(200);
        ctx.json(returnedOrderList);
    }
}
