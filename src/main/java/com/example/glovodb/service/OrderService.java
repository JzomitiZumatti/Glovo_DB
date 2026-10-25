package com.example.glovodb.service;

import com.example.glovodb.entity.Order;
import com.example.glovodb.entity.Product;
import com.example.glovodb.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public ResponseEntity<Order> getOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orders);
    }

    public ResponseEntity<Order> createOrder(Order newOrder) {
        Order savedOrder = orderRepository.save(newOrder);
        savedOrder.setTotalPrice(updateTotalSum(newOrder));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    public ResponseEntity<Order> updateOrder(int orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setName(updatedOrder.getName());
            existingOrder.setCustomerPhoneNumber(updatedOrder.getCustomerPhoneNumber());
            existingOrder.setAddress(updatedOrder.getAddress());
            existingOrder.setProducts(updatedOrder.getProducts());
            existingOrder.setTotalPrice(updateTotalSum(updatedOrder));
            orderRepository.save(existingOrder);
            return ResponseEntity.ok(existingOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Order> addProductToOrder(int orderId, Product newProduct) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getProducts().add(newProduct);
            order.setTotalPrice(updateTotalSum(order));
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Order> deleteProductFromOrder(int orderId, int productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            boolean productRemoved = order.getProducts().removeIf(product -> product.getId() == productId);
            if (productRemoved) {
                order.setTotalPrice(updateTotalSum(order));
                orderRepository.save(order);
                return ResponseEntity.ok(order);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Order> deleteOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok(orderOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private double updateTotalSum(Order order) {
        return order.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }
}
