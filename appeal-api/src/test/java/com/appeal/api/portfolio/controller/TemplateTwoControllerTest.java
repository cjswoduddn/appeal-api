package com.appeal.api.portfolio.controller;

import com.appeal.api.member.controller.MemberController;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import com.appeal.service.AwsS3Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {TemplateTwoController.class})
class TemplateTwoControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    PortfolioService portfolioService;
    @MockBean
    AwsS3Service s3Service;
    @MockBean
    AuthenticationProvider authenticationProvider;
    @MockBean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @MockBean
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Test
    @DisplayName("인증되지 않은 사용자는 여기 접근 불가능")
    public void noAuthenticatedUser() throws Exception{
        //given
        String url = "/portfolio/templatetwo";
        //when
        mvc.perform(post(url)).andExpect(status().is4xxClientError());
        //then
    }

    @Test
    @DisplayName("전송되는지만 일단")
    @WithMockUser // 이 자체만으로 인증된 유저로 테스트를 진행할 수 있게 됐다
    public void catchRequest() throws Exception{
        //given
        String url = "/portfolio/templatetwo";
        //when

        //then
    }
}