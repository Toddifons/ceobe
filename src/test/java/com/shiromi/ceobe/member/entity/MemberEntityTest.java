package com.shiromi.ceobe.member.entity;

import com.shiromi.ceobe.member.dto.MemberDTO;
import com.shiromi.ceobe.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemberEntityTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testToSaveEntity() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("test");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberEmail("test@test.com");
        memberDTO.setMemberName("홍길동");
        memberDTO.setMemberMobile("010-1234-5678");
        System.out.println(memberDTO);

        // when
        MemberEntity memberEntity = memberDTO.toEntity();
        memberRepository.save(memberEntity);


        MemberEntity saveMemberEntity = memberRepository
                .findByUserId(memberEntity.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("not found")
        );

        // then
        assertEquals(memberEntity.getUserId(), saveMemberEntity.getUserId());
        assertEquals(memberEntity.getMemberPassword(), saveMemberEntity.getMemberPassword());
        assertEquals(memberEntity.getMemberEmail(), saveMemberEntity.getMemberEmail());
        assertEquals(memberEntity.getMemberName(), saveMemberEntity.getMemberName());
        assertEquals(memberEntity.getMemberMobile(), saveMemberEntity.getMemberMobile());
    }

    // ... other test methods ...
}