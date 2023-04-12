package com.dotcat.springhw.service;

import com.dotcat.springhw.entity.Customer;
import com.dotcat.springhw.entity.UserOrder;

import java.util.List;

public interface CustomerService {
    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

    Customer getById(Long id);

    List<Customer> getAll();
}
