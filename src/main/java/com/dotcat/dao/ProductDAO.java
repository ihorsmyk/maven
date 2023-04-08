package com.dotcat.dao;

import com.dotcat.model.Product;

import java.util.List;

public interface ProductDAO {
    int create(Product product);

    Product getById(long id);

    int update(Product product);

    int deleteById(long id);
}
