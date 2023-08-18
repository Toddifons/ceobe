package com.shiromi.ceobe.order.repository;

import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.order.entity.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {


    List<OrderEntity> findByMemberEntity(MemberEntity memberEntity, Sort id);
}

