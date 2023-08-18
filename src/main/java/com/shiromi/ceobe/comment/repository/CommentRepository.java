package com.shiromi.ceobe.comment.repository;

import com.shiromi.ceobe.comment.entity.CommentEntity;
import com.shiromi.ceobe.item.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    Page<CommentEntity> findByItemEntity(ItemEntity itemEntity, PageRequest id);

}
