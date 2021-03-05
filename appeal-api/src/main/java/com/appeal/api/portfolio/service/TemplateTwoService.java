package com.appeal.api.portfolio.service;

import com.appeal.api.portfolio.dto.TemplateDto;
import com.appeal.api.portfolio.dto.TemplateTwoDto;
import com.appeal.api.portfolio.dto.TemplateTwoFileDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.TemplateTwo;
import com.appeal.api.portfolio.repository.TemplateTwoRepository;
import com.appeal.exception.notfound.NotFoundPortfolioException;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateTwoService implements TemplateService{

    private final AwsS3Service s3Service;   // 보기 너무 안 좋다
    private final TemplateTwoRepository templateTwoRepository;

    /**
     * Member는 생성정보를 통해 Portfolio에 반드시 들어가야 되는데 언제 넣어주는 게 정석일까?
     * 일단 도메인에서 시큐리티 컨텍스트를 직접 호출하는 건 테스트를 통해 도메인과 외부 기술의 강한 결합 때문에 별로라는 거 인식
     * 일단 잡다구리한 서비스 계층에서 궂은 일을 도맡아 해주는 걸로 가자
     */
    @Override
    public Long createTemplate(TemplateDto dto) {
        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl((TemplateTwoFileDto) dto, s3Service);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
