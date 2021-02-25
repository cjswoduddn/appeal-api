package com.appeal.api.portfolio.controller;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/portfolio")
@RequiredArgsConstructor
public abstract class PortfolioController<T> {

    protected final PortfolioService portfolioService;
    protected final AwsS3Service awsS3Service;

    public ResponseEntity<Long> createPortfolio(T dto){
        Portfolio portfolio = convertDtoToDomain(dto);
        return ResponseEntity.ok(
            portfolioService.savePortfolio(portfolio)
        );
    }

    protected abstract Portfolio convertDtoToDomain(T dto);

}
