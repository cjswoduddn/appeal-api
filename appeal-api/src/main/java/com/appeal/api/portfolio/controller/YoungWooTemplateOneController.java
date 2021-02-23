package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.YoungWooTemplateOneFileDto;
import com.appeal.api.common.dto.portfolio.YoungWooTemplateOneStringDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.YoungWooTemplateOne;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.service.AwsS3Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/youngwootemplateone")
public class YoungWooTemplateOneController extends PortfolioController<YoungWooTemplateOneFileDto> {

    public YoungWooTemplateOneController(PortfolioService portfolioService, AwsS3Service awsS3Service) {
        super(portfolioService, awsS3Service);
    }

    @Override
    protected Portfolio dtoToPortfolio(YoungWooTemplateOneFileDto dto) {
        YoungWooTemplateOneStringDto convertedDto = YoungWooTemplateOneStringDto.createDto(dto, awsS3Service);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Portfolio portfolio = YoungWooTemplateOne.createPortfolio(convertedDto, (Member)authentication.getPrincipal());
        return portfolio;
    }

}
