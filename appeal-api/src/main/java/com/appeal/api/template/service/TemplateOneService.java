package com.appeal.api.template.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.template.domain.templateone.TemplateOne;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.api.template.dto.templateone.TemplateOneDto;
import com.appeal.api.template.dto.templateone.TemplateOneFileDto;
import com.appeal.api.template.repository.TemplateOneRepository;
import com.appeal.code.ErrorCode;
import com.appeal.exception.NotAuthorizationException;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.exception.NotFoundPortfolioException;
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
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));

        TemplateOneDto templateOneDto = TemplateOneDto.convertFileDtoToStringUrl((TemplateOneFileDto) dto, s3Service);
        TemplateOne templateOne = TemplateOne.createTemplateOne(templateOneDto, member);
        templateOneRepository.save(templateOne);
        return templateOne.getId();
    }

    @Override
    public TemplateDto getTemplateById(Long id) {
        TemplateOne templateOne = templateOneRepository.findById(id)
                .orElseThrow(()-> new NotFoundPortfolioException(ErrorCode.NOT_FOUND_PORTFOLIO));
        return TemplateOneDto.convertDomainToDto(templateOne);
    }

    @Override
    public void deleteTemplate(MemberSession session, Long id) {
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
        TemplateOne templateOne = templateOneRepository.findById(id)
                .orElseThrow(()-> new NotFoundPortfolioException(ErrorCode.NOT_FOUND_PORTFOLIO));

        if(templateOne.getPortfolio().getMember() != member)
            throw new NotAuthorizationException(ErrorCode.NOT_AUTHORIZATION);
        templateOneRepository.delete(templateOne);
    }
}
