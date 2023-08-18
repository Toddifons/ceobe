package com.shiromi.ceobe.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //이전페이지 요청, 리퀘스트에서 "referer"라는 이름의 헤더를 변수에 대입
        String requestURL = request.getHeader("referer");

        log.info("requestURL = {}", requestURL);
        HttpSession session = request.getSession();

        if(session.getAttribute("member") == null) {
            response.sendRedirect("/member/login?redirectURL="+requestURL);
            //로그인 주소로 보내면서 로그인 끝나면 다시 돌아갈 주소도 함께 보냄
            return false;
        }
        return true;
    }
}
