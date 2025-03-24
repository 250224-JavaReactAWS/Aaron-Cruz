package com.ecommerce.repos;

import com.ecommerce.models.Status;
import com.ecommerce.util.ConnectionUtil;
import com.ecommerce.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM \"Order\" WHERE user_id = ?";
        List<Order> orderList = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order returnedOrder = new Order(
                            rs.getInt("order_id"),
                            rs.getInt("user_id"),
                            rs.getFloat("total_price"),
                            Status.valueOf(rs.getString("status")),
                            rs.getDate("created_at")
                    );
                    orderList.add(returnedOrder);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return orderList;
    }

    @Override
    public Order create(Order obj) {
        String sql = "";

        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return null;
                Order returnedOrder = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getFloat("total_price"),
                        Status.valueOf(rs.getString("status")),
                        rs.getDate("created_at")
                );
                return returnedOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order update(Order obj) {
        String sql = "";

        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return null;
                Order returnedOrder = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getFloat("total_price"),
                        Status.valueOf(rs.getString("status")),
                        rs.getDate("created_at")
                );
                return returnedOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
