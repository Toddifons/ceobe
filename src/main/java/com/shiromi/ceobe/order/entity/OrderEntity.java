package com.shiromi.ceobe.order.entity;

import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.orderItem.entity.OrderItemEntity;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    //    order(주문) : order_item(주문상품) = 1 : M
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemEntityList = new ArrayList<>();


    @Builder
    public OrderEntity(Long orderId, String orderName, String orderStatus, String review, MemberEntity memberEntity) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderStatus = orderStatus;
        this.review = review;
        this.memberEntity = memberEntity;
    }
}
