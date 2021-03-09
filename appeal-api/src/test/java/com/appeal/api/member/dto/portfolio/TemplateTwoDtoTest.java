package com.appeal.api.member.dto.portfolio;

import com.appeal.api.portfolio.dto.portfolio.PortfolioFileDto;
import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoDto;
import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoFileDto;
import com.appeal.exception.UnexpectedMethodArgumentNullPointerException;
import com.appeal.service.AwsS3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemplateTwoDtoTest {

    /**
     * TemplateTwoFileDto 완성본 만들기
     * s3servcie 모킹
     * 각 상황 별로 예외가 안 터지는지 확인하는 정도로 끝내자
     */

    AwsS3Service service;
    TemplateTwoFileDto templateTwoFileDto;

    @BeforeEach
    void before(){
        service = mock(AwsS3Service.class);
        templateTwoFileDto = new TemplateTwoFileDto();
        templateTwoFileDto.setPortfolio(new PortfolioFileDto());
    }

    @Test
    @DisplayName("성공테스트")
    public void checkStaticCreate() throws Exception{
        //given
        when(service.upload(any())).thenReturn("url");
        //when
        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl(templateTwoFileDto, service);
        //then
        assertNotNull(templateTwoDto);
    }
    @Test
    @DisplayName("그럴리는 없지만 포트폴리오가 널이라면?")
    public void portfolioFileDtoNull() throws Exception{
        //given
        templateTwoFileDto.setPortfolio(null);
        //when
        //then
        assertThrows(UnexpectedMethodArgumentNullPointerException.class,
                () -> TemplateTwoDto.convertFileDtoToStringUrl(templateTwoFileDto, service));
    }

}