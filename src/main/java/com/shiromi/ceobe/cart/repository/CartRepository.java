package com.shiromi.ceobe.cart.repository;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByMemberEntity(MemberEntity memberEntity);
}
