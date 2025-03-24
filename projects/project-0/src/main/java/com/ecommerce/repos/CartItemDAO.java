package com.ecommerce.repos;

import com.ecommerce.models.CartItem;

import java.util.List;

public interface CartItemDAO extends GeneralDAO<CartItem> {
    List<CartItem> addProductsToUserCart(List<CartItem> cartItemList);
}
