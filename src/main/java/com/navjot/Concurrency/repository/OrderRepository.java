package com.navjot.Concurrency.repository;

import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Procedure("GetPrice")
    double getPrice(int productId);

    List<Optional<OrderEntity>> getOrderEntityByName(String name);

    @Query(value = "SELECT 0.90 * price FROM OrderEntity WHERE productId = :productId") // , nativeQuery = true
    Optional<Double> getDiscountedPrice(@Param("productId") int productId);

    List<Double> getPriceByProductId(int productId);

}
