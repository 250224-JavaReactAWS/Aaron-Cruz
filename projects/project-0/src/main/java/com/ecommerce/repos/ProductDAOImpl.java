package com.ecommerce.repos;

import com.ecommerce.models.Product;
import com.ecommerce.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getAvailableProducts() {
        String sql = "SELECT * FROM \"Product\" WHERE stock > 0;";
        List<Product> availableProducts = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             Statement ps = conn.createStatement()) {

            try (ResultSet rs = ps.executeQuery(sql)) {
                while (rs.next()) {
                    availableProducts.add(new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getFloat("price"),
                            rs.getInt("stock")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableProducts;
    }

    @Override
    public Product create(Product obj) {
        String sql = "";

         try (Connection conn = ConnectionUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     Product returnedProduct = new Product(
                             rs.getInt("product_id"),
                             rs.getString("name"),
                             rs.getString("description"),
                             rs.getFloat("price"),
                             rs.getInt("stock")
                     );
                     return returnedProduct;
                 }
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
        return null;
    }

    @Override
    public Product update(Product obj) {
        String sql = "";

         try (Connection conn = ConnectionUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     Product returnedProduct = new Product(
                             rs.getInt("product_id"),
                             rs.getString("name"),
                             rs.getString("description"),
                             rs.getFloat("price"),
                             rs.getInt("stock")
                     );
                     return returnedProduct;
                 }
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
        return null;
    }
}
