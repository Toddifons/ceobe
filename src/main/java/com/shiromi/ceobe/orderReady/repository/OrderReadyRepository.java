package com.shiromi.ceobe.orderReady.repository;

import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.orderReady.entity.OrderReadyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderReadyRepository extends JpaRepository<OrderReadyEntity, Long> {
    List<OrderReadyEntity> findByMemberEntity(MemberEntity memberEntity);

    void deleteByMemberEntity(MemberEntity memberEntity);
}
