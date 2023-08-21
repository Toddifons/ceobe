package com.shiromi.ceobe.comment.entity;

import com.shiromi.ceobe.comment.dto.CommentDTO;
import com.shiromi.ceobe.item.entity.ItemEntity;
import com.shiromi.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String commentContents;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private int starCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private ItemEntity itemEntity;

    public static CommentEntity toCommentEntity(ItemEntity itemEntity, CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setItemEntity(itemEntity);
//        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setStarCount(commentDTO.getStarCount());
        return commentEntity;

    }
}
