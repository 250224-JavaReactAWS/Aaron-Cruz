package com.ecommerce.controllers;

import com.ecommerce.dtos.response.ErrorMessage;
import com.ecommerce.models.CartItem;
import com.ecommerce.services.CartItemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CartItemController {

    private final Logger logger = LoggerFactory.getLogger(CartItemController.class);

    private final CartItemService cartItemService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    public void addNewCartItemHandler(Context ctx) {
        try {
            Integer userId = ctx.sessionAttribute("user_id");

            if (userId == null) {
                ctx.status(401).json(new ErrorMessage("You must be logged to add items to your cart"));
                return;
            }

            List<CartItem> requestCartItems = objectMapper.readValue(ctx.body(),
                    new TypeReference<List<CartItem>>() {});

            List<CartItem> returnedCartItemList = cartItemService.addCartItems(requestCartItems);

//            requestCartItems.forEach(cartItem -> {
//                System.out.println("Producto ID: " + cartItem.getProductId() + ", Cantidad: " + cartItem.getQuantity());
//            });

            ctx.status(201).json(returnedCartItemList);
        } catch (Exception e) {
            ctx.status(400).json(new ErrorMessage(e.getMessage()));
        }
    }
}
