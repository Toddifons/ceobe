package com.shiromi.ceobe.member.repository;


import com.shiromi.ceobe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserId(String userId);
    Optional<MemberEntity> findByMemberName(String memberName);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
