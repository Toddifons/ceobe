package com.shiromi.ceobe.cartItem.repository;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.cartItem.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository  extends JpaRepository<CartItemEntity,Long> {
    List<CartItemEntity> findByCartEntity(CartEntity cartEntity1);
}