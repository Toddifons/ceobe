package com.shiromi.config.oauth;

import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.ceobe.member.repository.MemberRepository;

import java.util.Map;

import com.shiromi.config.auth.PrincipalDetails;
import com.shiromi.config.oauth.provider.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoderPwd;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String loginProvider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oauth2UserInfo = oauth2UserInfoCreate(loginProvider, oauth2User);

        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderID();
        String username = provider+"_"+providerId;
        //OAuth 로그인은 비밀번호를 저장하지 않음
        String password = encoderPwd.encode("Oauth2 Loging User");
        String email = oauth2UserInfo.getEmail();
        //String role = "ROLE_USER";

        MemberEntity memberEntity = memberRepository.findByMemberName(memberName);

        if(memberEntity != null) {
            System.out.println("이미 회원입니다. 이전에 OAuth로 회원가입을 진행했습니다.");
        } else {
            memberEntity = memberEntity.builder()
                    .memberName(memberEntity.getMemberName())
                    .memberPassword(memberEntity.getMemberPassword())
                    .email(email)
                    .role(RoleType.USER)
                    .provider(providerId)
                    .providerId(providerId)
                    .build();
            memberRepository.save(memberEntity);
        }

        return new PrincipalDetails(memberEntity, oauth2User.getAttributes());

    }

    private OAuth2UserInfo oauth2UserInfoCreate(String loginProvider, OAuth2User oauth2User) {
        OAuth2UserInfo oauth2UserInfo = null;

        if(loginProvider.equals("google")) {
            System.out.println("구글 로그인을 진행합니다.");
            oauth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());

        } else if(loginProvider.equals("facebook")) {
            System.out.println("페이스북 로그인을 진행합니다.");
            oauth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());

        } else if(loginProvider.equals("naver")){
            System.out.println("네이버 로그인을 진행합니다.");
            oauth2UserInfo = new NaverUserInfo((Map<String, Object>)oauth2User.getAttributes().get("response"));

        } else if(loginProvider.equals("kakao")){
            System.out.println("카카오 로그인을 진행합니다.");
            oauth2UserInfo = new KakaoUserInfo(oauth2User.getAttributes());

        } else {
            System.out.println("지원하지 않는 로그인입니다.");
        }

        return oauth2UserInfo;
    }


}