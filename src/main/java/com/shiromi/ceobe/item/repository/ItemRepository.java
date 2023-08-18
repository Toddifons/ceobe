package com.shiromi.ceobe.item.repository;

import com.shiromi.ceobe.item.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByItemName(String orderName);

    Page<ItemEntity> findByItemNameContaining(String search, PageRequest of);

    Page<ItemEntity> findByItemCategory(String small, PageRequest of);

}
