package com.example.digital_shop.repository.order;

import com.example.digital_shop.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;



@RestController
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> getOrderEntitiesByUserIdEquals(UUID userId);
}
