package com.ecommerce.util;

import com.ecommerce.controllers.OrderController;
import com.ecommerce.controllers.UserController;
import com.ecommerce.repos.OrderDAO;
import com.ecommerce.repos.OrderDAOImpl;
import com.ecommerce.repos.UserDAO;
import com.ecommerce.repos.UserDAOImpl;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinUtil {
    public static Javalin create(int port) {
        UserDAO userDAO = new UserDAOImpl();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

        OrderDAO orderDAO = new OrderDAOImpl();
        OrderService orderService = new OrderService(orderDAO);
        OrderController orderController = new OrderController(orderService);

        return Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("users", () -> {
                    post("/signup", userController :: registerUserHandler);
                    post("/login", userController :: loginHandler);
                    put("", userController :: updateUserHandler);
                });

                path("orders", () -> {
                    get("", orderController :: fetcherUserOrdersHandler);
                });
            });
        })
        .start(port);
    }
}
