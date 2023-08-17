package com.shiromi.ceobe.item.entity;

import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.item.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_table")
public class ItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int itemPrice;

    @Column(length = 500)
    private String itemContents;

    @Column
    private int itemCount = 0;


    @Column
    private int fileAttachedItem;

    @Column(length = 30)
    private String itemCategory;

    @Column
    private int itemSellCount = 0;

    @Builder
    public ItemEntity(Long id, String itemName, int itemPrice, String itemContents, int itemCount, int fileAttachedItem, String itemCategory, int itemSellCount) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemContents = itemContents;
        this.itemCount = itemCount;

        this.fileAttachedItem = fileAttachedItem;
        this.itemCategory = itemCategory;
        this.itemSellCount = itemSellCount;
    }

    public ItemDTO toItemDTO() {
        return ItemDTO.builder()
                .id(id)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemContents(itemContents)
                .itemCount(itemCount)
                .fileAttachedItem(fileAttachedItem)
                .itemCategory(itemCategory)
                .itemSellCount(itemSellCount)
                .build();
    }


    //save
    //saveFile
    //update
}
