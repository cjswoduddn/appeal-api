package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.common.dto.portfolio.TemplateTwoFileDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.TemplateTwo;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.service.AwsS3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateTwoController extends PortfolioController<TemplateTwoFileDto>{

    public TemplateTwoController(PortfolioService portfolioService, AwsS3Service awsS3Service) {
        super(portfolioService, awsS3Service);
    }

    @Override
    @PostMapping("/templatetwo")
    public ResponseEntity<Long> createPortfolio(TemplateTwoFileDto dto) {
        return super.createPortfolio(dto);
    }

    /**
     *  TemplateTwoDto를 파일 작업을 완료한 형태로 변환
     *  변환된 파일을 포트폴리오 형으로 변환
     */
    @Override
    protected Portfolio convertDtoToDomain(TemplateTwoFileDto dto) {
        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl(dto, awsS3Service);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return TemplateTwo.createTemplateTwo(templateTwoDto, (Member)authentication.getPrincipal());
    }
}
