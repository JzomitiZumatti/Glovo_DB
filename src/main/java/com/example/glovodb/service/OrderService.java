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

    public ResponseEntity<Object> getOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> getAllOrders() {
        List<Order> dbOrders = orderRepository.findAll();
        if (dbOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dbOrders, HttpStatus.OK);
    }

    public ResponseEntity<Object> createOrder(Order newOrder) {
        Order savedOrder = orderRepository.save(newOrder);

        if (savedOrder != null) {
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateOrder(int orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setName(updatedOrder.getName());
            existingOrder.setCustomerPhoneNumber(updatedOrder.getCustomerPhoneNumber());
            existingOrder.setAddress(updatedOrder.getAddress());
            existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
            existingOrder.setProducts(updatedOrder.getProducts());
            orderRepository.save(existingOrder);
            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> addProductToOrder(int orderId, Product newProduct) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getProducts().add(newProduct);
            orderRepository.save(order);
            return new ResponseEntity<>("Product added to order successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteProductFromOrder(int orderId, int productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            Collection<Product> products = order.getProducts();
            Optional<Product> productOptional = products.stream().filter(p -> p.getId() == productId).findFirst();
            if (productOptional.isPresent()) {
                products.remove(productOptional.get());
                orderRepository.save(order);
                return new ResponseEntity<>("Product deleted from order successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found in order", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
