package com.shiromi.config;


import com.shiromi.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Security 필터로 등록 = Security 설정을 해당 클래스에서 한다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // Spring Security 활성화 / 해당 메소드 실행 전 체크함.
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(authorize -> authorize
                        //== USER & ADMIN ==//
                        .antMatchers("/cart/saved",
                                "/cart/{id}",
                                "/cart/delete",
                                "/cart/change",
                                "/comment/save",
                                "/comment/update",
                                "/comment/delete",
                                "/item/save",
                                "/item/main",
                                "/item/update",
                                "/item/delete",
                                "/items",
                                "/item/",
                                "/member/password",
                                "/member/update",
                                "/order/save",
                                "/order/save2",
                                "/order/cart3",
                                "/order/cart2",
                                "/order/list",
                                "/question/list",
                                "/reply/save",
                                "/reply/delete"
                        ).hasAnyRole("USER", "ADMIN")

                        //== Only ADMIN ==//
                        .antMatchers("/member/admin",
                                "/member/list",
                                "/member/{id}",
                                "/member/password",
                                "/member/update",
                                "/member/mailConfirm",
                                "/member/searchPassword",
                                "/member/searchPasswordUpdate",
                                "/order/listAll",
                                "/order/status",
                                "/order/cancel",
                                "/question/save",
                                "/question",
                                "/question/update/{id}",
                                "/question/update",
                                "/question/delete/{id}"

                        ).hasRole("ADMIN")

                        /* //== ALL ==//
                        .antMatchers("/cart/list", "/item/category1", "/item/category2", "/item/category3",
                                "/member/save", "/member/duplicate-check-userId", "/member/login",
                                "/member/auth/login", "/member/logout", "/member/logout2", "/member/mailConfirm",
                                "/member/searchPassword", "/member/searchPasswordUpdate").permitAll()*/
                        .anyRequest().permitAll())

                .formLogin(formLogin -> formLogin.loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/"))

                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/auth/loginForm")
                        .userInfoEndpoint()
                        .userService(principalOauth2UserService))
                .build();
    }


}
