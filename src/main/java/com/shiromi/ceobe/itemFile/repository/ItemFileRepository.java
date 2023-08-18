package com.shiromi.ceobe.itemFile.repository;

import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.ceobe.itemFile.entity.ItemFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemFileRepository extends JpaRepository<ItemFileEntity,Long> {

    List<ItemEntity> findByItemEntity(ItemEntity itemEntity2);

    void deleteByItemEntity(ItemEntity itemEntity2);
}
