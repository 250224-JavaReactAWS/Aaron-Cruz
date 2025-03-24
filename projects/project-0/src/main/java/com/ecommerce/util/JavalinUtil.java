package com.ecommerce.util;

import com.ecommerce.controllers.CartItemController;
import com.ecommerce.controllers.OrderController;
import com.ecommerce.controllers.ProductController;
import com.ecommerce.controllers.UserController;
import com.ecommerce.repos.*;
import com.ecommerce.services.CartItemService;
import com.ecommerce.services.OrderService;
import com.ecommerce.services.ProductService;
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

        ProductDAO productDAO = new ProductDAOImpl();
        ProductService productService = new ProductService(productDAO);
        ProductController productController = new ProductController(productService);

        CartItemDAO cartItemDAO = new CartItemDAOImpl();
        CartItemService cartItemService = new CartItemService(cartItemDAO);
        CartItemController cartItemController = new CartItemController(cartItemService);

        return Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("users", () -> {
                    post("/signup", userController :: registerUserHandler);
                    post("/login", userController :: loginHandler);
                    put("", userController :: updateUserHandler);
                    post("/cart", cartItemController :: addNewCartItemHandler);
                });

                path("orders", () -> {
                    get("", orderController :: fetcherUserOrdersHandler);
                });

                path("products", () -> {
                    get("", productController :: fetcherAvailableProductsHandler);
                    post("", productController :: addNewProductHandler);
                    put("", productController :: refresherProductsHandler);
                    delete("", productController :: eraserUnavailableProductsHandler);
                });
            });
        })
        .start(port);
    }
}
