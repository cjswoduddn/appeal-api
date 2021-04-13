package com.appeal.api.template.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.api.template.dto.templatetwo.TemplateTwoDto;
import com.appeal.api.template.dto.templatetwo.TemplateTwoFileDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import com.appeal.api.template.repository.TemplateTwoRepository;
import com.appeal.code.ErrorCode;
import com.appeal.exception.NotAuthorizationException;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.exception.NotFoundPortfolioException;
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
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));

        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl((TemplateTwoFileDto) dto, s3Service);
        TemplateTwo templateTwo = TemplateTwo.createTemplateTwo(templateTwoDto, member);
        templateTwoRepository.save(templateTwo);
        return templateTwo.getId();
    }

    @Override
    public TemplateDto getTemplateById(Long id) {
        TemplateTwo templateTwo = templateTwoRepository.findById(id)
                .orElseThrow(()-> new  NotFoundPortfolioException(ErrorCode.NOT_FOUND_PORTFOLIO));
        return TemplateTwoDto.convertDomainToDto(templateTwo);
    }

    /**
     *  우선 하나의 포폴을 삭제하기 위해 수행하는 쿼리는 총 8방
     *  멤버를 조회하기 위한 1번 -> 필수
     *  템플릿+포트폴리오 1번 -> 필수
     *  템플릿의 커리어와 프로젝을 찾기 위해 각각 1번 -> 현 체제에서 필수
     *  템플릿, 포폴, 커리어, 프로젝을 모두 삭제 총 4번 -> 적합
     *  내 생각엔 삭제 연산이 매우 흔하지 않기 때문에 이정도는 괜찮다고 여겨지는데?
     */

    @Override
    public void deleteTemplate(MemberSession session, Long id) {
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
        TemplateTwo templateTwo = templateTwoRepository.findById(id)
                .orElseThrow(()-> new  NotFoundPortfolioException(ErrorCode.NOT_FOUND_PORTFOLIO));

        if(member != templateTwo.getPortfolio().getMember())
            throw new NotAuthorizationException(ErrorCode.NOT_AUTHORIZATION);
        templateTwoRepository.delete(templateTwo);
    }
}
