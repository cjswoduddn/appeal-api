package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.service.AwsS3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateTwoController extends PortfolioController<TemplateTwoDto>{

    public TemplateTwoController(PortfolioService portfolioService, AwsS3Service awsS3Service) {
        super(portfolioService, awsS3Service);
    }

    @Override
    @PostMapping("/templatetwo")
    public ResponseEntity<Long> createPortfolio(TemplateTwoDto dto) {
        return super.createPortfolio(dto);
    }

    @Override
    protected Portfolio dtoToPortfolio(TemplateTwoDto dto) {
        return null;
    }
}
