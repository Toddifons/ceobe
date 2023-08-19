package com.shiromi.common.controller;

import com.nimbusds.jose.proc.SecurityContext;
import com.shiromi.ceobe.member.entity.MemberEntity;
import com.shiromi.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalDetails principalDetails, HttpSession session) {
        try {
            MemberEntity memberEntity = principalDetails.getMemberEntity();
            if (memberEntity != null){
                session.setAttribute("member", memberEntity);
            }
        } catch (Exception e){
            e.getMessage();
        }
        return "index";
    }
}
