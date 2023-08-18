package com.shiromi.ceobe.item.dto;

import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.itemFile.entity.ItemFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    private List<MultipartFile> itemFile;
    private List<MultipartFile> itemFileUpdate;
    private List<String> originalFileNameItem;
    private List<String> storedFileNameItem;

    private String itemImage;
    private String userId;
    private int cartCount;
    private Long cartItemId;

    @Builder
    public ItemDTO(Long id, String itemName, int itemPrice, String itemContents, LocalDateTime itemCreatedDate, int itemCount, String itemCategory, int fileAttachedItem, int itemSellCount, List<MultipartFile> itemFile, List<MultipartFile> itemFileUpdate, List<String> originalFileNameItem, List<String> storedFileNameItem, String itemImage, String userId, int cartCount, Long cartItemId) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemContents = itemContents;
        this.itemCreatedDate = itemCreatedDate;
        this.itemCount = itemCount;
        this.itemCategory = itemCategory;
        this.fileAttachedItem = fileAttachedItem;
        this.itemSellCount = itemSellCount;
        this.itemFile = itemFile;
        this.itemFileUpdate = itemFileUpdate;
        this.originalFileNameItem = originalFileNameItem;
        this.storedFileNameItem = storedFileNameItem;
        this.itemImage = itemImage;
        this.userId = userId;
        this.cartCount = cartCount;
        this.cartItemId = cartItemId;
    }



    public static ItemDTO toItemDTO(ItemEntity itemEntity){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setItemName(itemEntity.getItemName());
        itemDTO.setItemPrice(itemEntity.getItemPrice());
        itemDTO.setItemContents(itemEntity.getItemContents());
        itemDTO.setItemCreatedDate(itemEntity.getCreatedTime());
        itemDTO.setItemCount(itemEntity.getItemCount());
        itemDTO.setItemCategory(itemEntity.getItemCategory());
        if(itemEntity.getFileAttachedItem()==1){
            itemDTO.setFileAttachedItem(itemEntity.getFileAttachedItem());
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for(ItemFileEntity itemFileEntity : itemEntity.getItemFileEntityList()){
                originalFileNameList.add(itemFileEntity.getOriginalFileNameItem());
                storedFileNameList.add(itemFileEntity.getStoredFileNameItem());
            }
            itemDTO.setOriginalFileNameItem(originalFileNameList);
            itemDTO.setStoredFileNameItem(storedFileNameList);
        }else{
            itemDTO.setFileAttachedItem(itemEntity.getFileAttachedItem());
        }
        return itemDTO;
    }



}
