package com.appeal.api.portfolio.controller;

import com.appeal.api.advice.GlobalExceptionHandler;
import com.appeal.api.portfolio.service.TemplateTwoService;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TemplateTwoController.class, GlobalExceptionHandler.class})
class TemplateTwoControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    TemplateTwoService templateTwoService;
    @MockBean
    AuthenticationProvider authenticationProvider;
    @MockBean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @MockBean
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    String TEMPLATE_URL = "/templatetwo";
    MockMultipartFile portfolioFileDtoThumbnail;

    @BeforeEach
    void before(){
        portfolioFileDtoThumbnail =
                new MockMultipartFile(
                        "portfolio.thumbnail",
                        "any.png",
                        MediaType.TEXT_PLAIN_VALUE,
                        "thumbnail".getBytes()
                );
    }

    @Test
    @DisplayName("인증되지 않으면 요청 불가능")
    public void isVaildUser() throws Exception{
        mvc.perform(
                multipart(TEMPLATE_URL)
        )
                .andExpect(status().is(403));
    }
    @Test
    @DisplayName("project는 적어도 하나의 필드를 가져야 함")
    @WithMockUser
    public void failRequest() throws Exception{
        mvc.perform(
                multipart(TEMPLATE_URL)
        )
                .andExpect(status().isBadRequest())
        ;
    }
    /**
     * 이슈 : 인증객체 안에 있는 인증 객체에 대해서는 @Valid가 먹히지 않는다. 생각해보자
     */


}