package com.navjot.Concurrency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.service.OrderFulfillmentService;
import com.navjot.Concurrency.service.OrderService;
import com.navjot.Concurrency.transformer.OrderDtoToOrderEntityTransformer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderFulfillmentController.class)
class OrderFulfillmentControllerMockMvcUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderFulfillmentService service;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderDtoToOrderEntityTransformer orderDtoToOrderEntityTransformer;

    @InjectMocks
    private OrderFulfillmentController orderFulfillmentController;


    @Test
    void processOrder() throws Exception {
        Order order = new Order(1, "Beta", "Hardware", 20, 1200.5, "");
        Mockito.when(service.processOrder(order)).thenReturn(order);
        Mockito.when(service.processOrder(any(Order.class))).thenReturn(order);
        Mockito.when(orderDtoToOrderEntityTransformer.apply(any(Order.class))).thenReturn(new OrderEntity());

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isOk());

        // Verify service interactions
        verify(service).processOrder(any(Order.class));
        verify(service).notifyUser(any(Order.class));
        verify(service).assignVendor(any(Order.class));
        verify(service).packaging(any(Order.class));
        verify(service).assignDeliveryPartner(any(Order.class));
        verify(service).assignTrailerAndDispatch(any(Order.class));
        verify(orderService).saveOrder(any(OrderEntity.class));
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