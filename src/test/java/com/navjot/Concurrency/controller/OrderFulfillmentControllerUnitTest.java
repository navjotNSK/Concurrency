package com.navjot.Concurrency.controller;

import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.service.OrderFulfillmentService;
import com.navjot.Concurrency.service.OrderService;
import com.navjot.Concurrency.transformer.OrderDtoToOrderEntityTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;

@ExtendWith(MockitoExtension.class)
class OrderFulfillmentControllerUnitTest {

    @InjectMocks
    private OrderFulfillmentController orderFulfillmentController;

    @Mock
    private OrderFulfillmentService service;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderDtoToOrderEntityTransformer orderDtoToOrderEntityTransformer;



    @Test
    void processOrder() throws Exception {
        Order order = new Order(1, "Beta", "Hardware", 20, 1200.5, "");
        OrderEntity orderEntity = OrderEntity.builder().productId(1).build();
        Mockito.when(orderDtoToOrderEntityTransformer.apply(order)).thenReturn(orderEntity);
        Mockito.doNothing().when(orderService).saveOrder(orderEntity);

        //Act
        ResponseEntity<Order> response = orderFulfillmentController.processOrder(order);


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