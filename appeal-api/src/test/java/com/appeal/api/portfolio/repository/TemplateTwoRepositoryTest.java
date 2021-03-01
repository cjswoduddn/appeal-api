package com.appeal.api.portfolio.repository;

import com.appeal.api.common.dto.portfolio.PortfolioDto;
import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.TemplateTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TemplateTwoRepositoryTest {

    @Autowired TemplateTwoRepository templateTwoRepository;
    TemplateTwo templateTwo;
    TemplateTwoDto templateTwoDto;

    @BeforeEach
    void before(){
        templateTwoDto = new TemplateTwoDto();
        templateTwoDto.setPortfolio(new PortfolioDto());
        templateTwo = TemplateTwo.createTemplateTwo(templateTwoDto);
    }

    @Test
    @DisplayName("TemplateTwo를통해 Portfolio를 저장할 때 PK가 잘 저장되는지")
    public void successCreate() throws Exception{
        //given
        templateTwoRepository.save(templateTwo);
        Portfolio portfolio = templateTwo.getPortfolio();
        //then
        assertEquals(portfolio.getId(), templateTwo.getId());
    }
}