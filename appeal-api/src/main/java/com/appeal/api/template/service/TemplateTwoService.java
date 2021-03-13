package com.appeal.api.template.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.api.template.dto.templatetwo.TemplateTwoDto;
import com.appeal.api.template.dto.templatetwo.TemplateTwoFileDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import com.appeal.api.template.repository.TemplateTwoRepository;
import com.appeal.exception.notfound.NotFoundMemberException;
import com.appeal.exception.notfound.NotFoundPortfolioException;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class TemplateTwoService implements TemplateService{

    private final AwsS3Service s3Service;   // 보기 너무 안 좋다
    private final TemplateTwoRepository templateTwoRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createTemplate(MemberSession session, TemplateDto dto) {
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException("없는 유저입니다"));

        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl((TemplateTwoFileDto) dto, s3Service);
        TemplateTwo templateTwo = TemplateTwo.createTemplateTwo(templateTwoDto, member);
        templateTwoRepository.save(templateTwo);
        return templateTwo.getId();
    }

    @Override
    public TemplateDto getTemplateById(Long id) {
        TemplateTwo templateTwo = templateTwoRepository.findById(id)
                .orElseThrow(()-> new  NotFoundPortfolioException("해당 포트폴리오는 없습니다"));
        return TemplateTwoDto.convertDomainToDto(templateTwo);
    }
}