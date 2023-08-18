package com.shiromi.ceobe.orderItem.entity;

import com.shiromi.common.entity.BaseEntity;
import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.order.entity.OrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_item_table")
public class OrderItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

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
