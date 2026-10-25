package com.example.glovodb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    private String address;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"))
    private Collection<Product> products;
}