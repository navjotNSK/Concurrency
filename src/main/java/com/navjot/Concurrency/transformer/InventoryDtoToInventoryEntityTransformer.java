package com.navjot.Concurrency.transformer;


import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.InventoryEntity;
import com.navjot.Concurrency.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryDtoToInventoryEntityTransformer {

    @Autowired
    private ModelMapper mapper;

    public InventoryEntity apply(Inventory inventory){
       return mapper.map(inventory, InventoryEntity.class);
    }


}
