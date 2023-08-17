package com.shiromi.ceobe.orderReady.entity;

import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="order_ready")
public class OrderReadyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int cartCount;

    @Column
    private Long cartItemId;

    @Column
    private String itemImage;

    @Column
    private int itemPriceTotal;

    @Column(length = 100, nullable = false)
    private String orderName;

    @Column
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

}
