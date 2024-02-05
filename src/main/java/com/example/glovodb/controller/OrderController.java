package com.example.glovodb.controller;

import com.example.glovodb.entity.Order;
import com.example.glovodb.entity.Product;
import com.example.glovodb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public ResponseEntity<Object> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(orderId, updatedOrder);
    }

    @PostMapping("/{orderId}/products")
    public ResponseEntity<Object> addProductToOrder(@PathVariable int orderId, @RequestBody Product newProduct) {
        return orderService.addProductToOrder(orderId, newProduct);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Object> deleteProductFromOrder(@PathVariable int orderId, @PathVariable int productId) {
        return orderService.deleteProductFromOrder(orderId, productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable int id) {
        return orderService.deleteOrderById(id);
    }
}
