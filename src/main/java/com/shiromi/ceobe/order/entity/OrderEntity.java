package com.shiromi.ceobe.order.entity;

import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(length = 100, nullable = false)
    private String orderName;
    @Column(length = 100, nullable = false)
    private String orderStatus;

    @Column(length = 10)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Builder
    public OrderEntity(Long orderId, String orderName, String orderStatus, String review, MemberEntity memberEntity) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderStatus = orderStatus;
        this.review = review;
        this.memberEntity = memberEntity;
    }
}
