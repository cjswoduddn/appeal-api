package com.appeal.api.member;

import com.appeal.api.advice.GlobalExceptionHandler;
import com.appeal.api.member.dto.SignUpDto;
import com.appeal.api.member.controller.MemberController;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.api.member.service.MemberService;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {MemberController.class, GlobalExceptionHandler.class})
class MemberControllerTest {
    @Autowired MockMvc mvc;
    @MockBean
    MemberService memberService;
    @MockBean
    AuthenticationProvider authenticationProvider;
    @MockBean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @MockBean
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    String requestUrl = "/member";

    SignUpDto signUpDto;
    UpdateMemberDto updateMemberDto;

    @BeforeEach
    void before(){
        signUpDto = new SignUpDto("ma8511@naver.com", "1234", "youngwoo");
        updateMemberDto = new UpdateMemberDto();
        updateMemberDto.setName("hello");
        updateMemberDto.setPassword("world");
    }

    @Test
    @DisplayName("정삭적인 요청")
    public void signUp() throws Exception{
        //given
        //when
        //then
        MvcResult result = mvc.perform(
                post(requestUrl)
                        .content(asJsonString(signUpDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn();
        String str = result.getResponse().getContentAsString();
        System.out.println("str = " + str);
    }

    @Test
    @DisplayName("이메일 형식에 대한 테스트")
    public void emailFormatFail() throws Exception{
        //given
        signUpDto.setEmail("noGoalBaeng2");
        //when
        //then
        mvc.perform(
                post(requestUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(signUpDto))
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("비밀번호 공백")
    public void passwordBlank() throws Exception{
        //given
        signUpDto.setPassword("");
        //when
        //then
        mvc.perform(
                post(requestUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signUpDto))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("이름공백")
    public void nameBlank() throws Exception{
        //given
        signUpDto.setPassword("");
        //when
        //then
        mvc.perform(
                post(requestUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signUpDto))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("id를 넘기지 않은 요청")
    @WithMockUser
    public void idBlank() throws Exception{
        //given
        //when
        mvc.perform(
                put(requestUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateMemberDto))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isMethodNotAllowed());

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