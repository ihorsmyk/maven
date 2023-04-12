package com.dotcat.springhw.service.impl;

import com.dotcat.springhw.entity.Product;
import com.dotcat.springhw.entity.UserOrder;
import com.dotcat.springhw.repository.OrderRepository;
import com.dotcat.springhw.repository.ProductRepository;
import com.dotcat.springhw.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(UserOrder order) {
        orderRepository.save(order);
    }

    @Override
    public void update(UserOrder order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(UserOrder order) {
        orderRepository.delete(order);
    }

    @Override
    public UserOrder getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void formOrder() {
        UserOrder order = new UserOrder();
        Product product = productRepository.findById(1l).get();
        order.getProducts().add(product);
        orderRepository.save(order);
    }
}
