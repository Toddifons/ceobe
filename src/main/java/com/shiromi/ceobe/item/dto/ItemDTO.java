package com.shiromi.ceobe.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ItemDTO {
    private Long id;
    private String itemName;
    private int itemPrice;
    private String itemContents;
    private LocalDateTime itemCreatedDate;
    private int itemCount;
    private String itemCategory;
    private int fileAttachedItem;

    private int itemSellCount;



    @Builder
    public ItemDTO(Long id, String itemName, int itemPrice, String itemContents, LocalDateTime itemCreatedDate, int itemCount, String itemCategory, int fileAttachedItem, int itemSellCount) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemContents = itemContents;
        this.itemCreatedDate = itemCreatedDate;
        this.itemCount = itemCount;
        this.itemCategory = itemCategory;
        this.fileAttachedItem = fileAttachedItem;
        this.itemSellCount = itemSellCount;
    }


}
