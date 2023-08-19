package com.shiromi.ceobe.member.dto;

import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.config.auth.RoleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String userId;
    private String memberPassword;
    private String memberEmail;
    private String memberName;
    private String memberMobile;
    private String memberAddress;
    private String detailAddress;
    private String extraAddress;
    private String postcode;
    private RoleType role;
    private LocalDateTime memberCreatedTime;
    private LocalDateTime memberUpdatedTime;

    private String accessToken;
    private String url;


    public MemberDTO(Long id, String userId, String memberEmail, String memberName, String memberMobile, LocalDateTime memberCreatedTime) {
        this.id = id;
        this.userId = userId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        this.memberCreatedTime = memberCreatedTime;
    }
    @Builder
    public MemberDTO(Long id, String userId, String memberPassword, String memberEmail, String memberName, String memberMobile, String memberAddress, String detailAddress, String extraAddress, String postcode, LocalDateTime memberCreatedTime, LocalDateTime memberUpdatedTime, RoleType role, String accessToken, String url) {
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
        this.role = role;
        this.memberCreatedTime = memberCreatedTime;
        this.memberUpdatedTime = memberUpdatedTime;
        this.accessToken = accessToken;
        this.url = url;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .userId(userId)
                .memberPassword(memberPassword)
                .memberEmail(memberEmail)
                .memberName(memberName)
                .memberMobile(memberMobile)
                .build();
    }

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setDetailAddress(memberEntity.getDetailAddress());
        memberDTO.setExtraAddress(memberEntity.getExtraAddress());
        memberDTO.setPostcode(memberEntity.getPostcode());
        memberDTO.setMemberCreatedTime(memberEntity.getCreatedTime());
        memberDTO.setMemberUpdatedTime(memberEntity.getUpdatedTime());
        return memberDTO;
    }
}

