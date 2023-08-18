package com.shiromi.ceobe.cartItem.entity;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.item.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item_table")
@Setter
@Getter
public class CartItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String cartName;

    @Column(nullable = false)
    private int cartCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;



}

