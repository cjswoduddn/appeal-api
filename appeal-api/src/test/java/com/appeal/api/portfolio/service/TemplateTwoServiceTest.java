package com.appeal.api.portfolio.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import com.appeal.api.template.dto.templatetwo.TemplateTwoDto;
import com.appeal.api.template.dto.templatetwo.TemplateTwoFileDto;
import com.appeal.api.template.repository.TemplateTwoRepository;
import com.appeal.api.template.service.TemplateTwoService;
import com.appeal.service.AwsS3Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.Mockito.*;

class TemplateTwoServiceTest {

    TemplateTwoService service;
    AwsS3Service awsS3Service;
    TemplateTwoRepository templateTwoRepository;
    MemberRepository memberRepository;

    MockedStatic<TemplateTwoDto> templateTwoDto;
    MockedStatic<TemplateTwo> templateTwo;

    Member member;
    TemplateTwoFileDto ttfd = new TemplateTwoFileDto();
    TemplateTwoDto ttd = new TemplateTwoDto();
    TemplateTwo tt;

    @BeforeEach
    void before(){

        awsS3Service = mock(AwsS3Service.class);
        templateTwoRepository = mock(TemplateTwoRepository.class);
        memberRepository = mock(MemberRepository.class);
        templateTwoDto = mockStatic(TemplateTwoDto.class);
        templateTwo = mockStatic(TemplateTwo.class);

        member = Member.createMember(new MemberDto());
        service = new TemplateTwoService(awsS3Service, templateTwoRepository, memberRepository);
    }

    @AfterEach
    void after(){
        templateTwoDto.close();
    }

    @Test
    @DisplayName("스태틱메서드 모킹 테스트")
    public void createTemplate() throws Exception{
        //given
        MemberSession session = new MemberSession();
        session.setId(1L);
        when(awsS3Service.upload(any(MultipartFile.class))).thenReturn("uploaded");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(TemplateTwoDto.convertFileDtoToStringUrl(ttfd, awsS3Service)).thenReturn(ttd);
        when(TemplateTwo.createTemplateTwo(ttd, member)).thenReturn(tt);

        //when
//        service.createTemplate(session, ttfd);
        //then
    }
}