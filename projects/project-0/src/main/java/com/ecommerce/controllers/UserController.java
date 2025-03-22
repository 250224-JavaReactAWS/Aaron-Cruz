package com.ecommerce.controllers;

import com.ecommerce.dtos.response.ErrorMessage;
import com.ecommerce.models.User;
import com.ecommerce.services.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    public void registerUserHandler(Context ctx) {
        User requestUser = ctx.bodyAsClass(User.class);

        // Register the user and log them in
        User registeredUser = userService.registerNewUser(
                requestUser.getFirstName(),
                requestUser.getLastName(),
                requestUser.getEmail(),
                requestUser.getPassword());

        // If something fails we'll report a server side error
        if (registeredUser == null){
            ctx.status(500);
            ctx.json(new ErrorMessage("Something went wrong!"));
            return;
        }

        logger.info("New user registered with email: " + registeredUser.getEmail());

        ctx.status(201);
        ctx.json(registeredUser);
    }

}
