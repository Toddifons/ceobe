package com.shiromi.ceobe.question.entity;

import com.shiromi.common.entity.BaseEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "question_answer")
public class ReplyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionAnswerId;

    @Column(length = 20, nullable = false)
    private String replyName;

    @Column(length = 500)
    private String replyContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;
}
