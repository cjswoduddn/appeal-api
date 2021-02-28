package com.appeal.api.portfolio.controller;


import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.TemplateTwo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

class PortfolioControllerTest {
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
    @DisplayName("fjfh")
    public void test(){
        Portfolio portfolio = mock(Portfolio.class);
        TemplateTwoDto dto = new TemplateTwoDto();
        when(TemplateTwoDto.convertFileDtoToStringUrl(any(), any())).thenReturn(any());
        when(TemplateTwo.createTemplateTwo(dto, any(Member.class))).thenReturn(any());
    }

}