package com.navjot.Concurrency.controller;


import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.InventoryEntity;
import com.navjot.Concurrency.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderFulfillmentControllerIntegrationTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    void processOrder() throws Exception {
        Order order = new Order(1, "Beta", "Hardware", 20, 1200.5, "");

        String url = "http://localhost:" + port + "/orders?mediaType=json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> request = new HttpEntity<>(order, headers);

        InventoryEntity inventoryEntity = InventoryEntity.builder().productId(1).build();

//        testEntityManager.persist(inventoryEntity);
         inventoryRepository.save(inventoryEntity);

        // Act

//        ResponseEntity<Order> response =  restTemplate.postForEntity(url,request,Order.class);
        ResponseEntity<Order> response = restTemplate.exchange(url, HttpMethod.POST, request, Order.class);

//        if (response.getStatusCode() == HttpStatus.OK) {
//            Order returnedOrder = response.getBody();
//            // assertions on returnedOrder
//        } else {
//            System.out.println("Received non-OK response: " + response.getStatusCode());
//            return;
//        }

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Order returnedOrder = response.getBody();
        Assertions.assertEquals(order.getProductId(), returnedOrder.getProductId());
        Assertions.assertEquals(order.getName(), returnedOrder.getName());
        Assertions.assertEquals(order.getProductType(), returnedOrder.getProductType());
        Assertions.assertEquals(order.getQty(), returnedOrder.getQty());
        Assertions.assertEquals(order.getPrice(), returnedOrder.getPrice());
    }

    @Test
    void getOrderByName() {
    }

    @Test
    void getOrderByName1() {
    }

    @Test
    void getDiscountedPriceByProductId() {
    }
}