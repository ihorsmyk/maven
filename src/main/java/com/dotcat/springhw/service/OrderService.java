package com.dotcat.springhw.service;

import com.dotcat.springhw.entity.UserOrder;

public interface OrderService {
    void create(UserOrder order);

    void update(UserOrder order);

    void delete(UserOrder order);

    UserOrder getById(Long id);

    void formOrder();
}
