package com.dotcat.service.impl;

import com.dotcat.dao.ProductDAO;
import com.dotcat.dao.impl.ProductDAOImpl;
import com.dotcat.model.Product;
import com.dotcat.service.ProductService;


public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public int create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product getById(long id) {
        return productDAO.getById(id);
    }

    @Override
    public int update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public int deleteById(long id) {
        return productDAO.deleteById(id);
    }
}
