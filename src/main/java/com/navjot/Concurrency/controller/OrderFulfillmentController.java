package com.navjot.Concurrency.controller;

import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.service.OrderFulfillmentService;
import com.navjot.Concurrency.service.OrderService;
import com.navjot.Concurrency.transformer.OrderDtoToOrderEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller - Spring MVC  ,   @RestController - Spring Json or xml Responses
//If we use Controller Generic annotation instead of RestController then we need to explicitly define @ResponseBody annotation
//on methods return xml or json responses because without that method will look for Views as Return type and will give
//exception - ViewNotFoundException. But if you are using ResponseEntity<T> as Return type then you will not get exception
//as ResponseEntity will explicitly define Json responses so you will not get any error.
@Controller
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

    @Cacheable("orders")
    @GetMapping(value = "/{name}" , produces = { "application/json", "application/xml" })
    public ResponseEntity<List<Order>> getOrderByName(@PathVariable String name){
        return new ResponseEntity<>(orderService.getOrderByName(name), HttpStatusCode.valueOf(200));
    }


    //Without ResponseBody you will get ViewNotFoundException as it is not returning ResponseEntity so it will try to find
    //List<Order>.view hence exception so ResponseBody is mandatory here.
    @GetMapping(value = "o1/{name}" )
    @ResponseBody
    public List<Order> getOrderByName1(@PathVariable String name){
        return orderService.getOrderByName(name);
    }



    @GetMapping("/getDiscountedPrice")
    public ResponseEntity<Double> getDiscountedPriceByProductId(@RequestParam int productId, @RequestParam boolean discountedPrice){
        return new ResponseEntity<>(orderService.getDiscountedPrice(productId , discountedPrice), HttpStatusCode.valueOf(200))  ;
    }
}