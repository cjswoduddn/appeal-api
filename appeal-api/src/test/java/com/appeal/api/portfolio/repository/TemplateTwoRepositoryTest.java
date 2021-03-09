package com.appeal.api.portfolio.repository;

import com.appeal.api.member.dto.SignUpDto;
import com.appeal.api.portfolio.dto.portfolio.PortfolioDto;
import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoCareerDto;
import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoDto;
import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoProjectDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.templatetwo.TemplateTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TemplateTwoRepositoryTest {

    @Autowired TemplateTwoRepository templateTwoRepository;
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    TemplateTwo templateTwo;
    TemplateTwoDto templateTwoDto;

    @BeforeEach
    void before(){
        templateTwoDto = new TemplateTwoDto();
        templateTwoDto.setPortfolio(new PortfolioDto());
        templateTwoDto.getProjects().add(TemplateTwoProjectDto.builder().build());
        templateTwoDto.getProjects().add(TemplateTwoProjectDto.builder().build());
        templateTwoDto.getProjects().add(TemplateTwoProjectDto.builder().build());
        templateTwoDto.getCareers().add(TemplateTwoCareerDto.builder().build());
        Member member = Member.createMember(new SignUpDto());
        memberRepository.save(member);
        templateTwo = TemplateTwo.createTemplateTwo(templateTwoDto, member);
    }

    @Test
    @DisplayName("TemplateTwo를 통해 Portfolio를 저장할 때 PK가 잘 저장되는지")
    public void successCreate() throws Exception{
        //given
        templateTwoRepository.save(templateTwo);
        Portfolio portfolio = templateTwo.getPortfolio();
        //then
        assertEquals(portfolio.getId(), templateTwo.getId());
    }

    @Test
    @DisplayName("페치조인 테스트")
    public void findById() throws Exception{
        //given
        templateTwoRepository.save(templateTwo);
        em.flush();
        em.clear();
        TemplateTwo templateTwo = templateTwoRepository.findById(this.templateTwo.getId()).orElseThrow(NullPointerException::new);
        templateTwo.getProjects().forEach(project-> System.out.println("project = " + project.getName()));
    }
}