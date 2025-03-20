package com.ecommerce.repos;

import com.ecommerce.models.User;

public class UserDAOImpl implements UserDAO{
    @Override
    public User create(User obj) {
        return obj;
    }
}
