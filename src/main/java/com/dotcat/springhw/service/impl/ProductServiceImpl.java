package com.dotcat.springhw.service.impl;

import com.dotcat.springhw.entity.Product;
import com.dotcat.springhw.repository.ProductRepository;
import com.dotcat.springhw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }
}
