package com.shiromi.ceobe.item.entity;

import com.shiromi.ceobe.common.entity.BaseEntity;
import com.shiromi.ceobe.item.dto.ItemDTO;
import com.shiromi.ceobe.itemFile.entity.ItemFileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "item_table")
@NoArgsConstructor
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

    //item(상품) : item_file(상품이미지) = 1 : M
    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemFileEntity> itemFileEntityList = new ArrayList<>();


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

    public static ItemEntity toItemSaveEntity(ItemDTO itemDTO) {
        ItemEntity itementity = new ItemEntity();
        itementity.setItemName(itemDTO.getItemName());
        itementity.setItemPrice(itemDTO.getItemPrice());
        itementity.setItemContents(itemDTO.getItemContents());
        itementity.setItemCount(itemDTO.getItemCount());
        itementity.setItemCategory(itemDTO.getItemCategory());
        itementity.setFileAttachedItem(0);
        return itementity;
    }

    public static ItemEntity toItemSaveFileEntity(ItemDTO itemDTO) {
        ItemEntity itementity = new ItemEntity();
        itementity.setItemName(itemDTO.getItemName());
        itementity.setItemPrice(itemDTO.getItemPrice());
        itementity.setItemContents(itemDTO.getItemContents());
        itementity.setItemCount(itemDTO.getItemCount());
        itementity.setItemCategory(itemDTO.getItemCategory());
        itementity.setFileAttachedItem(1);
        return itementity;
    }
    public static ItemEntity toItemUpdateEntity(ItemDTO itemDTO) {
        ItemEntity itementity = new ItemEntity();
        itementity.setId(itemDTO.getId());
        itementity.setItemName(itemDTO.getItemName());
        itementity.setItemPrice(itemDTO.getItemPrice());
        itementity.setItemContents(itemDTO.getItemContents());
        itementity.setItemCount(itemDTO.getItemCount());
        itementity.setItemCategory(itemDTO.getItemCategory());
        itementity.setFileAttachedItem(1);
        return itementity;
    }


    //save
    //saveFile
    //update
}
