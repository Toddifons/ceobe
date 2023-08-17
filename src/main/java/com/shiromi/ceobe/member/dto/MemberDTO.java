package com.shiromi.ceobe.member.dto;

import com.shiromi.ceobe.member.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private LocalDateTime memberCreatedTime;
    private LocalDateTime memberUpdatedTime;

    private String accessToken;
    private String url;

    @Builder
    public MemberDTO(Long id, String userId, String memberPassword, String memberEmail, String memberName, String memberMobile, String memberAddress, String detailAddress, String extraAddress, String postcode, LocalDateTime memberCreatedTime, LocalDateTime memberUpdatedTime, String accessToken, String url) {
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
        this.memberCreatedTime = memberCreatedTime;
        this.memberUpdatedTime = memberUpdatedTime;
        this.accessToken = accessToken;
        this.url = url;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
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

