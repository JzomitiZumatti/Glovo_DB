package com.example.glovodb.controller;

import com.example.glovodb.entity.Order;
import com.example.glovodb.entity.Product;
import com.example.glovodb.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(orderId, updatedOrder);
    }

    @PatchMapping("/{orderId}/products")
    public ResponseEntity<Order> addProductToOrder(@PathVariable int orderId, @RequestBody Product newProduct) {
        return orderService.addProductToOrder(orderId, newProduct);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Order> deleteProductFromOrder(@PathVariable int orderId, @PathVariable int productId) {
        return orderService.deleteProductFromOrder(orderId, productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable int id) {
        return orderService.deleteOrderById(id);
    }
}
