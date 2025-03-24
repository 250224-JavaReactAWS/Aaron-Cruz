package com.ecommerce.services;

import com.ecommerce.models.Product;
import com.ecommerce.repos.ProductDAO;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> fetchAvailableProducts() {
        return productDAO.getAvailableProducts();
    }

    public Product addProduct(Product product) {
        return productDAO.create(product);
    }

    public Product updateProduct(Product product) {
        return productDAO.update(product);
    }

    public boolean eraseUnavailableProducts() {
        return  productDAO.deleteUnavailableProducts();
    }
}
