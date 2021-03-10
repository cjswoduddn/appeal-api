package com.appeal.api.portfolio.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.portfolio.domain.templateone.TemplateOne;
import com.appeal.api.portfolio.dto.template.TemplateDto;
import com.appeal.api.portfolio.dto.template.templateone.TemplateOneDto;
import com.appeal.api.portfolio.dto.template.templateone.TemplateOneFileDto;
import com.appeal.api.portfolio.repository.TemplateOneRepository;
import com.appeal.exception.notfound.NotFoundMemberException;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateOneService implements TemplateService{

    private final AwsS3Service s3Service;   // 보기 너무 안 좋다
    private final TemplateOneRepository templateOneRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createTemplate(MemberSession session, TemplateDto dto) {
        TemplateOneDto templateOneDto = TemplateOneDto.convertFileDtoToStringUrl((TemplateOneFileDto) dto, s3Service);
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException("없는 유저입니다"));
        TemplateOne templateOne = TemplateOne.createTemplateOne(templateOneDto, member);
        templateOneRepository.save(templateOne);
        return templateOne.getId();
    }

    @Override
    public TemplateDto getTemplateById(Long id) {
        return null;
    }
}
