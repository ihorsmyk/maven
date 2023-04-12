package com.dotcat.springhw.repository;

import com.dotcat.springhw.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
