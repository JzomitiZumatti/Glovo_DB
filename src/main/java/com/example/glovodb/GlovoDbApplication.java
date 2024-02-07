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
                    GET /api - retrieve all orders
                    GET /api/{id} - retrieve an order by id
                    POST /api - create a new order
                    PUT /api/{orderId} - update an existing order
                    PATCH /api/{orderId}/products - add a product to an order
                    DELETE /api/{orderId}/products/{productId} - delete a product from an order
                    DELETE /api/{id} - delete an order by id
                """);
    }
}
