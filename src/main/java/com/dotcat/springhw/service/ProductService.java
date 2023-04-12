package com.dotcat.springhw.service;

import com.dotcat.springhw.entity.Product;

public interface ProductService {
    void create(Product product);

    void update(Product product);

    void delete(Product product);

    Product getById(Long id);
}
