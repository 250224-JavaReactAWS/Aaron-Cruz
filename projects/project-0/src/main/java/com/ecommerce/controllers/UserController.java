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

    public void loginHandler(Context ctx) {
        User requestUser = ctx.bodyAsClass(User.class);
        User returnedUser = userService.loginUser(requestUser.getEmail(), requestUser.getPassword());

        if (returnedUser == null){
            ctx.json(new ErrorMessage("Username or Password Incorrect"));
            ctx.status(400);
            return;
        }

        logger.info("A successful login has been recorded for the user identified by " +
                returnedUser.getUserId());

        ctx.status(200);
        ctx.json(returnedUser);

        ctx.sessionAttribute("user_id", returnedUser.getUserId());
        ctx.sessionAttribute("role", returnedUser.getRole());
    }

    public void updateUserHandler(Context ctx) {

        Integer userId = ctx.sessionAttribute("user_id");

        if (userId == null) {
            ctx.status(401);
            ctx.json(new ErrorMessage("You must be logged to udpate your info"));
            return;
        }

        User requestUser = ctx.bodyAsClass(User.class);
        requestUser.setUserId(userId.intValue());
        User returnedUser = userService.updateUserInfo(requestUser);

        if (returnedUser == null) {
            ctx.status(400);
            ctx.json(new ErrorMessage("The user does not exist"));
            return;
        }

        logger.info("A successful update has been recorded foe the user identified by " +
                returnedUser.getUserId());

        ctx.status(200);
        ctx.json(returnedUser);

    }

}
