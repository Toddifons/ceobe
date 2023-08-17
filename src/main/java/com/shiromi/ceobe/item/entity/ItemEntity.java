package com.shiromi.ceobe.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_table")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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



    //save
    //saveFile
    //update
}
