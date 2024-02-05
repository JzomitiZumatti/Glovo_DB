package com.example.glovodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class GlovoDbApplication {

    static final Logger logger = LogManager.getLogger(GlovoDbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GlovoDbApplication.class, args);
        logger.info("""
                Welcome to order manager!
                Available methods:
                    GET /api/order/getOrders - retrieve all orders
                    GET /api/order/{id} - retrieve an order by id
                    POST /api/order/create - create a new order
                    PUT /api/order/{orderId} - update an existing order
                    POST /api/order/{orderId}/products - add a product to an order
                    DELETE /api/order/{orderId}/products/{productId} - delete a product from an order
                    DELETE /api/order/{id} - delete an order by id
                """);
    }
}
