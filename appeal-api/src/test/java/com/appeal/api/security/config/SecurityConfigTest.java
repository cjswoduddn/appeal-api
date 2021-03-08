package com.appeal.api.security.config;

import com.appeal.api.advice.GlobalExceptionHandler;
import com.appeal.api.member.controller.MemberController;
import com.appeal.api.member.dto.SignInDto;
import com.appeal.api.member.service.MemberService;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {MemberController.class, GlobalExceptionHandler.class})
class SecurityConfigTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    MemberService memberService;
    @MockBean
    AuthenticationProvider authenticationProvider;
    @MockBean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @MockBean
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    String requestUrl = "/signin";

    @Test
    @DisplayName("제이슨 잘 오는지 체크")
    public void signinTest() throws Exception{
        //given
        String email = "email";
        String password = "password";
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail(email);
        signInDto.setPassword(password);

        //when
        mvc.perform(
                post(requestUrl)
                        .content(asJsonString(signInDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                ;

        //then
    }
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}

/**
 * 2021 03 06 변경사항
 * 기존 ObjectMapper를 이용해 Json을 읽어올 때 Map에 데이터를 받았었다
 * 깔끔하지 못한 방법이라고 항상 생각했고 SignInDto를 만들어서 나름대로 잘 구현
 * 오늘 마저 끝내야 할 일 : 멤버 업데이트와 멤버 삭제
 * 세션 저장 어떻게 하는지 좀만 자세히 공부 (굳이 도메인을 세션에 넣을 필요 없다는 생각)
 * 도메인에 넣은 implement sirialize없애기
 */