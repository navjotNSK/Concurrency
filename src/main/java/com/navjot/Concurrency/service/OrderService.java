package com.navjot.Concurrency.service;


import com.navjot.Concurrency.dto.Order;
import com.navjot.Concurrency.entity.InventoryEntity;
import com.navjot.Concurrency.entity.OrderEntity;
import com.navjot.Concurrency.exception.ProductIdNotFoundException;
import com.navjot.Concurrency.repository.InventoryRepository;
import com.navjot.Concurrency.repository.OrderRepository;
import com.navjot.Concurrency.transformer.OrderEntityToOrderDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderEntityToOrderDtoTransformer orderEntityToOrderDtoTransformer;

    private InventoryRepository inventoryRepository;

    @Autowired
    public OrderService(@Lazy OrderRepository orderRepository , OrderEntityToOrderDtoTransformer orderEntityToOrderDtoTransformer , InventoryRepository inventoryRepository){
        this.orderRepository = orderRepository;
        this.orderEntityToOrderDtoTransformer = orderEntityToOrderDtoTransformer;
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional
    public void saveOrder(OrderEntity orderEntity){
        orderRepository.save(orderEntity);
        InventoryEntity inventoryEntity = inventoryRepository.getInventoryEntityByProductId(orderEntity.getProductId()).stream().map(i->i.get()).findFirst().orElse(null);

        Optional.ofNullable(inventoryEntity).map(e->{
        int currentQty = inventoryEntity.getStockQty();
        inventoryEntity.setStockQty(currentQty - orderEntity.getQty());
               return inventoryRepository.save(e);}

        ).orElseThrow(()->new ProductIdNotFoundException("Inventory does not exist."));
    }

    public List<Order> getOrderByName(String name){
        return orderRepository.getOrderEntityByName(name).stream().map(o->orderEntityToOrderDtoTransformer.apply(o.get())).toList();
    }


    public double getDiscountedPrice(int productId , boolean discountedPrice) {
        return discountedPrice ? orderRepository.getDiscountedPrice(productId).orElseThrow(() ->new ProductIdNotFoundException("Product Id not found")) : orderRepository.getPriceByProductId(productId).get(0);
    }
}
