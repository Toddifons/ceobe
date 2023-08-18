package com.shiromi.ceobe.cart.entity;

import com.shiromi.ceobe.cartItem.entity.CartItemEntity;
import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_table")
@Setter
@Getter
public class CartEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    //    cart(장바구니) : cart_item(장바구니 상품) = 1 : M
    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItemEntityList = new ArrayList<>();



}
