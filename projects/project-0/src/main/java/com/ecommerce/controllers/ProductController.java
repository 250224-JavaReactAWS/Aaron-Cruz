package com.ecommerce.controllers;

import com.ecommerce.dtos.response.ErrorMessage;
import com.ecommerce.models.Product;
import com.ecommerce.models.Role;
import com.ecommerce.services.ProductService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void fetcherAvailableProductsHandler(Context ctx) {
        if (ctx.sessionAttribute("user_id") == null) {
            ctx.status(401);
            ctx.json(new ErrorMessage("You must be logged to create available products"));
            return;
        }

        List<Product> returnedProductList = productService.fetchAvailableProducts();

        ctx.status(200);
        ctx.json(returnedProductList);
    }

    public void addNewProductHandler(Context ctx) {
        if (ctx.sessionAttribute("user_id") == null) {
            ctx.status(401);
            ctx.json(new ErrorMessage("You must be logged to view available products"));
            return;
        }
        if (!ctx.sessionAttribute("role").equals(Role.ADMIN)) {
            ctx.status(401);
            ctx.json(new ErrorMessage("You must be an Admin to create available products"));
            return;
        }

        Product requestProduct = ctx.bodyAsClass(Product.class);
        Product returnedProduct = productService.addProduct(requestProduct);

        ctx.status(200);
        ctx.json(returnedProduct);
    }
}
