package com.navjot.Concurrency.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.service.OrderFulfillmentService;
import com.navjot.Concurrency.service.OrderService;
import com.navjot.Concurrency.transformer.OrderDtoToOrderEntityTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderFulfillmentControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderFulfillmentService service;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderDtoToOrderEntityTransformer orderDtoToOrderEntityTransformer;

    @InjectMocks
    private OrderFulfillmentController orderFulfillmentController;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = standaloneSetup(orderFulfillmentController).build();
    }

    @Test
    void processOrder() throws Exception {
        Order order = new Order(1, "Beta", "Hardware", 20, 1200.5, "");
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