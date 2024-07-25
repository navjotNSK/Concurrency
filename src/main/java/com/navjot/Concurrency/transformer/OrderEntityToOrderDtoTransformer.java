package com.navjot.Concurrency.transformer;


import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToOrderDtoTransformer {

    @Autowired
    private ModelMapper mapper;

    public Order apply(OrderEntity order){
       return mapper.map(order,Order.class);
    }


}
