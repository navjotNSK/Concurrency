package com.navjot.Concurrency.repository;

import com.navjot.Concurrency.entity.InventoryEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// Not working
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class InventoryRepositoryIntegrationTwoTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        // This ensures a clean state for each test
        entityManager.clear();
    }

    @Test
    void getInventoryEntityByProductId() {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setProductId(1);
        inventoryRepository.saveAndFlush(inventoryEntity);

        //Act
        List<Optional<InventoryEntity>> inventoryEntityByProductId = inventoryRepository.getInventoryEntityByProductId(1);
        InventoryEntity inventoryEntity2 = inventoryEntityByProductId.stream().map(i->i.get()).findFirst().orElse(null);

        //Assert
        Assertions.assertFalse(inventoryEntityByProductId.isEmpty(), "Inventory list should not be empty");
        Assertions.assertEquals(1, inventoryEntityByProductId.size(), "Inventory size mismatch");
        Assertions.assertEquals(1, inventoryEntityByProductId.get(0).get().getProductId(), "Product ID mismatch");
    }
}
