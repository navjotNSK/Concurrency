package com.navjot.Concurrency.service;

import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.repository.InventoryRepository;
import com.navjot.Concurrency.transformer.InventoryDtoToInventoryEntityTransformer;
import com.navjot.Concurrency.transformer.InventoryEntityToInventoryDtoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryDtoToInventoryEntityTransformer inventoryDtoToInventoryEntityTransformer;

    public boolean checkProductAvailability(int productId) {
        return true;
    }

    public void saveInventory(Inventory inventory){
        inventoryRepository.save(inventoryDtoToInventoryEntityTransformer.apply(inventory));
    }
}