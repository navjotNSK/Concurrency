package com.navjot.Concurrency.controller;

import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.InventoryEntity;
import com.navjot.Concurrency.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> processOrder(@RequestBody Inventory inventory) throws InterruptedException {
        inventoryService.saveInventory(inventory);
        return ResponseEntity.ok(inventory);
    }
}
