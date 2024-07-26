package com.navjot.Concurrency.transformer;


import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.entity.InventoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryEntityToInventoryDtoTransformer {

    @Autowired
    private ModelMapper mapper;

    public Inventory apply(InventoryEntity inventoryEntity){
       return mapper.map(inventoryEntity, Inventory.class);
    }


}
