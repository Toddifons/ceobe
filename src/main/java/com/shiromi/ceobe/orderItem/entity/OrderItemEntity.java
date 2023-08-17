package com.shiromi.ceobe.orderItem.entity;

import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.order.entity.OrderEntity;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(length = 100, nullable = false)
    private String orderName;

    @Column(nullable = false)
    private int orderCount;

    @Column(nullable = false)
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
