package com.ecommerce.services;

import com.ecommerce.models.CartItem;
import com.ecommerce.repos.CartItemDAO;

import java.util.List;

public class CartItemService {

    private CartItemDAO cartItemDAO;

    public CartItemService(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    public List<CartItem> addCartItems(List<CartItem> cartItemList) {
        return cartItemDAO.addProductsToUserCart(cartItemList);
    }
}
