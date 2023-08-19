package com.shiromi.ceobe.member.service;

import com.shiromi.ceobe.cart.entity.CartEntity;
import com.shiromi.ceobe.cart.repository.CartRepository;
import com.shiromi.ceobe.member.dto.MemberDTO;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.member.repository.MemberRepository;
import com.shiromi.config.auth.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    //== 회원 가입 ==//
    public Long signup(MemberDTO memberDTO) {
       MemberEntity saveMember = MemberEntity.toSaveEntity(memberDTO);
       saveMember.setRole(RoleType.USER);

       MemberEntity findMember = memberRepository.findById(saveMember.getId())
               .orElseThrow(() -> new IllegalArgumentException("Member Not Found"));

       if (findMember != null){
           log.info("이미 회원입니다.");
       } else {
           memberRepository.save(saveMember);
       }

//        Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
//        Optional<MemberEntity> memberEntity = memberRepository.findById(savedId);
//        if (memberEntity.isPresent()) {
//            MemberEntity memberEntity1 = memberEntity.get();
//            CartEntity cartEntity = new CartEntity();
//            cartEntity.setMemberEntity(memberEntity1);
//            cartRepository.save(cartEntity);
//        }

        return saveMember.getId();
    }

    public MemberDTO memberLogin(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(memberDTO.getUserId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toDTO(memberEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //== 중복 id 검사 ==//
    public String userIdDuplicateCheck(String userId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(userId);
        if (optionalMemberEntity.isPresent()) {
            return "fail";
        } else {
            return "success";
        }
    }

    public Page<MemberDTO> findAll(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        final int pageLimit = 5;
        Page<MemberEntity> memberEntities = memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<MemberDTO> memberDTOPage = memberEntities.map(
                member -> new MemberDTO(
                        member.getId(),
                        member.getUserId(),
                        member.getMemberEmail(),
                        member.getMemberName(),
                        member.getMemberMobile(),
                        member.getCreatedTime()));
        return memberDTOPage;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if (memberEntity.isPresent()) {
            MemberEntity memberEntity1 = memberEntity.get();
            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity1);
            return memberDTO;
        } else {
            return null;
        }
    }

    public MemberDTO findByUserId(String userId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(userId);
        if (optionalMemberEntity.isPresent()) {

            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        MemberEntity updateEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(updateEntity);
    }

    public MemberDTO saveKakao(MemberDTO memberDTO) {
        Optional<MemberEntity> memberEntity2 = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (memberEntity2.isEmpty()) {
            MemberDTO memberDTO1 = new MemberDTO();
            memberDTO1.setMemberEmail(memberDTO.getMemberEmail());
            memberDTO1.setUserId(memberDTO.getMemberName());
            memberDTO1.setMemberName(memberDTO.getMemberName());
            memberDTO1.setMemberPassword(memberDTO.getUserId());
            memberDTO1.setMemberMobile("000-0000-0000");
            Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO1)).getId();
            Optional<MemberEntity> memberEntity = memberRepository.findById(savedId);
            if (memberEntity.isPresent()) {
                MemberEntity memberEntity1 = memberEntity.get();
                CartEntity cartEntity = new CartEntity();
                cartEntity.setMemberEntity(memberEntity1);
                cartRepository.save(cartEntity);
                return MemberDTO.toDTO(memberEntity1);
            }
        } else {
            return MemberDTO.toDTO(memberEntity2.get());

        }
        return null;
    }

    public String memberEmailDuplicateCheck(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()) {
            return "fail";
        } else {
            return "success";
        }
    }

    public String findByMemberEmail(String email) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(email);
        if (optionalMemberEntity.isPresent()) {
            return "success";
        } else {
            return "fail";
        }
    }

    public void passwordUpdate(String memberEmail, String code) {
        System.out.println("서비스넘어옴");
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        memberEntity.setMemberPassword(code);
        memberRepository.save(memberEntity);
    }
}