package com.ecommerce.services;

import com.ecommerce.models.User;
import com.ecommerce.repos.UserDAO;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerNewUser(String firstName, String lastName, String email, String password) {
        User newUser = new User(firstName, lastName, email, password);
        return userDAO.create(newUser);
    }

    public User loginUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user.getPassword().equals(password)) return user;
        return null;
    }

    public User updateUserInfo(User user) {
        return userDAO.update(user);
    }
}
