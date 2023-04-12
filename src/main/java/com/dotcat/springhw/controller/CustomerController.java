package com.dotcat.springhw.controller;

import com.dotcat.springhw.entity.Customer;
import com.dotcat.springhw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void create(@RequestBody Customer customer) {
        customerService.create(customer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        if (customer != null) {
            customerService.delete(customer);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }
}
