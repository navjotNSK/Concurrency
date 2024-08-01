package com.navjot.Concurrency.repository;

import com.navjot.Concurrency.entity.InventoryEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InventoryRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setup() {
        // This ensures a clean state for each test
        testEntityManager.clear();
    }

    @Test
    void getInventoryEntityByProductId() {
        InventoryEntity inventoryEntity = InventoryEntity.builder().productId(1).build();
        testEntityManager.persist(inventoryEntity);

        //Act
        List<Optional<InventoryEntity>> inventoryEntityByProductId = inventoryRepository.getInventoryEntityByProductId(1);
        InventoryEntity inventoryEntity2 = inventoryEntityByProductId.stream().map(i->i.get()).findFirst().orElse(null);

        //Assert
        Assertions.assertEquals(inventoryEntityByProductId.stream().map(i->i.get()).findFirst().orElse(null).getProductId() , 1);
        Assertions.assertEquals(1, inventoryEntityByProductId.size());
        Assertions.assertEquals(1, inventoryEntity2.getProductId());

    }
}