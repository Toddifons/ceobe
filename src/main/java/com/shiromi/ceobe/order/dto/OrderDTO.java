package com.shiromi.ceobe.order.dto;

import com.shiromi.ceobe.common.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseEntity {
    private Long id;
    private int orderCount;
    private String orderName;
    private String memberAddress;
    private String detailAddress;
    private String extraAddress;
    private String postcode;
    private String memberMobile;
    private String orderStatus = "주문완료";
    private int orderPrice;
    private LocalDateTime orderCreatedTime;

    private String userId;
    private Long itemId;
    private Long memberId;
    private String memberName;
    private String review = "리뷰작성";

    @Builder
    public OrderDTO(Long id, int orderCount, String orderName, String memberAddress, String detailAddress, String extraAddress, String postcode, String memberMobile, String orderStatus, int orderPrice, LocalDateTime orderCreatedTime, String userId, Long itemId, Long memberId, String memberName, String review) {
        this.id = id;
        this.orderCount = orderCount;
        this.orderName = orderName;
        this.memberAddress = memberAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.postcode = postcode;
        this.memberMobile = memberMobile;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
        this.orderCreatedTime = orderCreatedTime;
        this.userId = userId;
        this.itemId = itemId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.review = review;
    }


}

