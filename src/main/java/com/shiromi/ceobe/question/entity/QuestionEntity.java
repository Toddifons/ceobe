package com.shiromi.ceobe.question.entity;

import com.shiromi.ceobe.question.dto.QuestionDTO;
import com.shiromi.common.entity.BaseEntity;
import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "question")
public class QuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @Column(length = 20)
    private String questionName;

    @Column(length = 4)
    private String questionStatus;

    @Column(length = 50)
    private String questionTitle;

    @Column(length = 500)
    private String questionContents;

    @Column(length = 6)
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    //== ReplyEntity가 없어서 우선 주석 처리 ==//
//    //    question(질문) : reply(답변) = 1 : M
//    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<ReplyEntity> replyEntityList = new ArrayList<>();

//    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO , MemberEntity memberEntity){
////        QuestionEntity questionEntity = new QuestionEntity();
////        questionEntity.setQuestionName(questionDTO.getQuestionName());
////        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
////        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
////        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
////        questionEntity.setMemberEntity(memberEntity);
////        return questionEntity;
//    }

}
