package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.TemplateTwo;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import com.appeal.service.AwsS3Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {TemplateTwoController.class})
class TemplateTwoControllerTest {

    @Autowired
    TemplateTwoController templateTwoController;
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

    MockedStatic<TemplateTwoDto> templateTwoDtoMockedStatic;
    MockedStatic<TemplateTwo> templateTwoMockedStatic;
    @BeforeEach
    void beforeClass(){
        templateTwoDtoMockedStatic = mockStatic(TemplateTwoDto.class);
        templateTwoMockedStatic = mockStatic(TemplateTwo.class);
    }
    @AfterEach
    void afterClass(){
        templateTwoDtoMockedStatic.close();
        templateTwoMockedStatic.close();
    }



    @Test
    @DisplayName("인증되지 않은 사용자는 여기 접근 불가능")
    public void noAuthenticatedUser() throws Exception{
        //given
        String url = "/portfolio/templatetwo";
        //when
        mvc.perform(post(url)).andExpect(status().is4xxClientError());
        //then
    }

    /**
     *  1) dto를 받고 제대로 기능이 처리되었는지 체크해보기
     *  2) dto를 변환하는데 이 기능은 전부 위임이므로 넘긴다
     *
     */
    @Test
    @DisplayName("전송되는지만 일단")
    @WithMockUser // 이 자체만으로 인증된 유저로 테스트를 진행할 수 있게 됐다
    public void catchRequest() throws Exception{
        //given
        String url = "/portfolio/templatetwo";
        Portfolio portfolio = mock(Portfolio.class);
        TemplateTwoDto dto = new TemplateTwoDto();
        when(TemplateTwoDto.convertFileDtoToStringUrl(any(), any())).thenReturn(any());
        when(TemplateTwo.createTemplateTwo(dto, any(Member.class))).thenReturn(any());
//        when(portfolioService.savePortfolio(portfolio)).thenReturn(anyLong());
        //then
//        mvc.perform(post(url)).andExpect(status().isOk());
    }
}