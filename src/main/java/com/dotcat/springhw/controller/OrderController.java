package com.dotcat.springhw.controller;

import com.dotcat.springhw.entity.UserOrder;
import com.dotcat.springhw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void create(@RequestBody UserOrder order) {
        orderService.create(order);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserOrder> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PutMapping
    public void formOrder() {
        orderService.formOrder();
    }
}
