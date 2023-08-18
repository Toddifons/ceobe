package com.shiromi.ceobe.member.entity;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.common.entity.BaseEntity;
import com.shiromi.ceobe.member.dto.MemberDTO;
import com.shiromi.ceobe.order.entity.OrderEntity;
import com.shiromi.ceobe.question.entity.QuestionEntity;
import com.shiromi.ceobe.question.entity.ReplyEntity;
import com.shiromi.config.auth.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "member_table")
@NoArgsConstructor
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; //고유번호

    @Column(length = 20, nullable = false, unique = true)
    private String userId; //멤버 아이디 (문자열)

    @Column(length = 30, nullable = false)
    private String memberPassword; //패스워드

    @Column(length = 30, nullable = false)
    private String memberEmail; //이메일

    @Column(length = 20, nullable = false)
    private String memberName; //닉네임

    @Column(length = 20, nullable = false)
    private String memberMobile; //전화번호


    @Column(length = 100)
    private String memberAddress;

    @Column(length = 100)
    private String detailAddress;

    @Column(length = 100)
    private String extraAddress;

    @Column(length = 100)
    private String postcode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    //    member(회원) : order(주문) = 1 : M
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    //    member(회원) : cart(장바구니) = 1 : M
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartEntity> cartEntityList = new ArrayList<>();

    //    member(회원) : question(질문) = 1 : M
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionEntity> questionEntityList = new ArrayList<>();

    //    member(회원) : reply(답변) = 1 : M
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    @Builder
    public MemberEntity(Long id, String userId, String memberPassword, String memberEmail, String memberName, String memberMobile, String memberAddress, String detailAddress, String extraAddress, String postcode, List<OrderEntity> orderEntityList, List<CartEntity> cartEntityList, List<QuestionEntity> questionEntityList, List<ReplyEntity> replyEntityList, RoleType role) {
        this.id = id;
        this.userId = userId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        this.memberAddress = memberAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.postcode = postcode;
        this.orderEntityList = orderEntityList;
        this.cartEntityList = cartEntityList;
        this.questionEntityList = questionEntityList;
        this.replyEntityList = replyEntityList;
        this.role = role;
    }

    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setDetailAddress(memberDTO.getDetailAddress());
        memberEntity.setExtraAddress(memberDTO.getExtraAddress());
        memberEntity.setPostcode(memberDTO.getPostcode());
        return memberEntity;
    }
    public static MemberEntity toUpdateEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        memberEntity.setDetailAddress(memberDTO.getDetailAddress());
        memberEntity.setExtraAddress(memberDTO.getExtraAddress());
        memberEntity.setPostcode(memberDTO.getPostcode());
        return memberEntity;
    }
}