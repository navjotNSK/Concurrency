package com.navjot.Concurrency.controller;

import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.service.OrderFulfillmentService;
import com.navjot.Concurrency.service.OrderService;
import com.navjot.Concurrency.transformer.OrderDtoToOrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderFulfillmentController {


    @Autowired
    private OrderFulfillmentService service;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDtoToOrderEntityTransformer orderDtoToOrderEntityTransformer;

    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
        service.processOrder(order); // synchronous
        // asynchronous
        service.notifyUser(order);
        service.assignVendor(order);
        service.packaging(order);
        service.assignDeliveryPartner(order);
        service.assignTrailerAndDispatch(order);
        orderService.saveOrder(orderDtoToOrderEntityTransformer.apply(order));
        return ResponseEntity.ok(order);
    }


    @GetMapping(value = "/{name}" , produces = { "application/json", "application/xml" })
    public ResponseEntity<List<Order>> getOrderByName(@PathVariable String name){
        return new ResponseEntity<>(orderService.getOrderByName(name), HttpStatusCode.valueOf(200));
    }



    @GetMapping("/getDiscountedPrice")
    public ResponseEntity<Double> getDiscountedPriceByProductId(@RequestParam int productId, @RequestParam boolean discountedPrice){
        return new ResponseEntity<>(orderService.getDiscountedPrice(productId , discountedPrice), HttpStatusCode.valueOf(200))  ;
    }
}