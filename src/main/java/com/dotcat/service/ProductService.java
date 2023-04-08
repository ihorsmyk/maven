package com.dotcat.service;

import com.dotcat.model.Product;

import java.util.List;

public interface ProductService {
    int create(Product product);

    Product getById(long id);

    int update(Product product);

    int deleteById(long id);
}
