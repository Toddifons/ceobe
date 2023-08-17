package com.shiromi.ceobe.cart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
    private Long id;
    private String userId;
    private int cartCount;

}
