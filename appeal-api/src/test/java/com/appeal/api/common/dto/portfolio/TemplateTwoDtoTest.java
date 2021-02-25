package com.appeal.api.common.dto.portfolio;

import com.appeal.service.AwsS3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemplateTwoDtoTest {

    TemplateTwoFileDto templateTwoFileDto;

    AwsS3Service awsS3Service;
    PortfolioFileDto portfolioFileDto;
    TemplateTwoProjectFileDto templateTwoProjectFileDto;
    TemplateTwoCareerDto templateTwoCareerDto;
    MultipartFile file;
    String targetString = "targetString";

    @BeforeEach
    void before(){
        file = new MockMultipartFile("hello.text", "fj".getBytes());
        awsS3Service = mock(AwsS3Service.class);
        portfolioFileDto = PortfolioFileDto.builder()
                .intro("intro")
                .name("name")
                .skill("skill")
                .thumbnail(file)
                .title("title")
                .build();
        templateTwoProjectFileDto = TemplateTwoProjectFileDto.builder()
                .intro("intro")
                .name("name")
                .role("role")
                .thumbnail(file)
                .build();
        templateTwoCareerDto = TemplateTwoCareerDto.builder()
                .date("date")
                .intro("intro")
                .position("position")
                .stack("stack")
                .title("title")
                .build();
        List<TemplateTwoProjectFileDto> projects = new ArrayList<>();
        projects.add(templateTwoProjectFileDto);
        List<TemplateTwoCareerDto> careers = new ArrayList<>();
        careers.add(templateTwoCareerDto);
        templateTwoFileDto = new TemplateTwoFileDto();
        templateTwoFileDto.setPortfolio(portfolioFileDto);
        templateTwoFileDto.setProjects(projects);
        templateTwoFileDto.setCareers(careers);
    }

    @Test
    @DisplayName("하자가 없을 때 TemplateTwoDto가 잘 만들어지는가?")
    public void success() throws Exception{
        //given
        //when
        when(awsS3Service.upload(file)).thenReturn(targetString);
        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl(templateTwoFileDto, awsS3Service);

        //then
        checkPortfolioDto(templateTwoDto.getPortfolio(), portfolioFileDto);
    }

    private void checkPortfolioDto(PortfolioDto portfolioDto, PortfolioFileDto portfolioFileDto) {
        assertEquals(portfolioDto.getIntro(), portfolioFileDto.getIntro());
        assertEquals(portfolioDto.getName(), portfolioFileDto.getName());
        assertEquals(portfolioDto.getSkill(), portfolioFileDto.getSkill());
        assertEquals(portfolioDto.getTitle(), portfolioFileDto.getTitle());
        assertEquals(portfolioDto.getThumbnail(), targetString);
    }

    @Test
    @DisplayName("Portfolio")
    public void makeTemplateTwoWhenPortfolioFileDtoFileNull() throws Exception{
        //given

        //when

        //then
    }

}