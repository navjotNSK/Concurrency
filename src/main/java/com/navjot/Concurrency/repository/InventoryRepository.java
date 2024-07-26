package com.navjot.Concurrency.repository;

import com.navjot.Concurrency.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    List<Optional<InventoryEntity>> getInventoryEntityByProductId(Integer id);

}
