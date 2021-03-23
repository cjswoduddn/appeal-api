package com.appeal.api.template.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.template.domain.templateone.TemplateOne;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.api.template.dto.templateone.TemplateOneDto;
import com.appeal.api.template.dto.templateone.TemplateOneFileDto;
import com.appeal.api.template.repository.TemplateOneRepository;
import com.appeal.exception.NoAuthorizationException;
import com.appeal.exception.notfound.NotFoundMemberException;
import com.appeal.exception.notfound.NotFoundPortfolioException;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateOneService implements TemplateService{

    private final AwsS3Service s3Service;   // 보기 너무 안 좋다
    private final TemplateOneRepository templateOneRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createTemplate(MemberSession session, TemplateDto dto) {
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException("없는 유저입니다"));

        TemplateOneDto templateOneDto = TemplateOneDto.convertFileDtoToStringUrl((TemplateOneFileDto) dto, s3Service);
        TemplateOne templateOne = TemplateOne.createTemplateOne(templateOneDto, member);
        templateOneRepository.save(templateOne);
        return templateOne.getId();
    }

    @Override
    public TemplateDto getTemplateById(Long id) {
        TemplateOne templateOne = templateOneRepository.findById(id)
                .orElseThrow(()-> new NotFoundPortfolioException("해당 포트폴리오는 없습니다"));
        return TemplateOneDto.convertDomainToDto(templateOne);
    }

    @Override
    public void deleteTemplate(MemberSession session, Long id) {
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException("없는 유저입니다"));
        TemplateOne templateOne = templateOneRepository.findById(id)
                .orElseThrow(()-> new NotFoundPortfolioException("해당 포트폴리오는 없습니다"));

        if(templateOne.getPortfolio().getMember() != member)
            throw new NoAuthorizationException("삭제 권한이 없습니다");
        templateOneRepository.delete(templateOne);
    }
}
