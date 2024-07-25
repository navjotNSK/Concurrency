package com.navjot.Concurrency.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {

    public boolean checkProductAvailability(int productId) {
        return true;
    }
}