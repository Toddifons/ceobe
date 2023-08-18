package com.shiromi.ceobe.orderItem.repository;

import com.shiromi.ceobe.order.entity.OrderEntity;
import com.shiromi.ceobe.orderItem.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    OrderItemEntity findByOrderEntity(OrderEntity orderEntity);
}
