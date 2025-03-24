package com.ecommerce.repos;

import com.ecommerce.models.Product;

import java.util.List;

public interface ProductDAO extends GeneralDAO<Product>{
    List<Product> getAvailableProducts();
}
