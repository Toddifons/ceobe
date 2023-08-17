package com.shiromi.ceobe.member.entity;

import com.shiromi.ceobe.member.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public MemberEntity(Long id, String userId, String memberPassword, String memberEmail, String memberName, String memberMobile, String memberAddress, String detailAddress, String extraAddress, String postcode) {
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
    }

    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .id(id)
                .userId(userId)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberMobile(memberMobile)
                .memberAddress(memberAddress)
                .detailAddress(detailAddress)
                .extraAddress(extraAddress)
                .postcode(postcode)
                .build();
    }
}
