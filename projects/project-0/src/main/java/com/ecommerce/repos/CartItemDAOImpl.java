package com.ecommerce.repos;

import com.ecommerce.models.CartItem;
import com.ecommerce.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    @Override
    public List<CartItem> addProductsToUserCart(List<CartItem> cartItemList) {
        String sql = "INSERT INTO \"CartItem\" (user_id, product_id, quantity) VALUES (?, ?, ?) RETURNING *";
        List<CartItem> returnedCartItemList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (CartItem cartItem : cartItemList) {
                ps.setInt(1, cartItem.getUserId());
                ps.setInt(2, cartItem.getProductId());
                ps.setInt(3, cartItem.getQuantity());

                // Execute and get result
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        returnedCartItemList.add(new CartItem(
                                rs.getInt("cart_item_id"),
                                rs.getInt("user_id"),
                                rs.getInt("product_id"),
                                rs.getInt("quantity")
                        ));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnedCartItemList;
    }


    @Override
    public CartItem create(CartItem obj) {
        return null;
    }

    @Override
    public CartItem update(CartItem obj) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
