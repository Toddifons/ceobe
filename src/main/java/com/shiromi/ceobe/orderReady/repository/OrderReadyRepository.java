package com.shiromi.ceobe.orderReady.repository;

import com.shiromi.ceobe.orderReady.entity.OrderReadyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReadyRepository extends JpaRepository<OrderReadyEntity, Long> {
}
