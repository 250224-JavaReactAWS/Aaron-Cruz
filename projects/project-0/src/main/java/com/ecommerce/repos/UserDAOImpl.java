package com.ecommerce.repos;

import com.ecommerce.models.Role;
import com.ecommerce.models.User;
import com.ecommerce.util.ConnectionUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User create(User obj) {
        String sql = "INSERT INTO public.\"User\" (first_name, last_name, email, password) " +
                "VALUES (?, ?, ?, ?) RETURNING user_id";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, obj.getFirstName());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setUserId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM public.\"User\" WHERE email = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User returnedUser =  new User(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                    returnedUser.setUserId(rs.getInt("user_id"));
                    returnedUser.setRole(Role.valueOf(rs.getString("role")));
                    return returnedUser;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE \"User\" SET first_name = ?, last_name = ?, email = ?, password = ?" +
                " WHERE user_id = ? RETURNING *";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getUserId());

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    User returnedUser =  new User(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                    returnedUser.setUserId(rs.getInt("user_id"));
                    returnedUser.setRole(Role.valueOf(rs.getString("role")));
                    return returnedUser;
                }
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
