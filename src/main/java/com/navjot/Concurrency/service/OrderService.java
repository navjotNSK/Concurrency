package com.navjot.Concurrency.service;


import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.exception.ProductIdNotFoundException;
import com.navjot.Concurrency.repository.OrderRepository;
import com.navjot.Concurrency.transformer.OrderEntityToOrderDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderEntityToOrderDtoTransformer orderEntityToOrderDtoTransformer;

    @Autowired
    public OrderService(@Lazy OrderRepository orderRepository , OrderEntityToOrderDtoTransformer orderEntityToOrderDtoTransformer){
        this.orderRepository = orderRepository;
        this.orderEntityToOrderDtoTransformer = orderEntityToOrderDtoTransformer;
    }


    public void saveOrder(OrderEntity orderEntity){
        orderRepository.save(orderEntity);
    }

    public List<Order> getOrderByName(String name){
        return orderRepository.getOrderEntityByName(name).stream().map(o->orderEntityToOrderDtoTransformer.apply(o.get())).toList();
    }


    public double getDiscountedPrice(int productId , boolean discountedPrice) {
        return discountedPrice ? orderRepository.getDiscountedPrice(productId).orElseThrow(() ->new ProductIdNotFoundException("Product Id not found")) : orderRepository.getPriceByProductId(productId).get(0);
    }
}
