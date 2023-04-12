package com.dotcat.springhw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "Decimal(7,2)")
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<UserOrder> orders = new HashSet<>();
}
