package com.appeal.api.portfolio.service;

import com.appeal.api.common.dto.portfolio.PortfolioResponseDto;
import com.appeal.exception.NoPortfolioFoundException;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public Long savePortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio).getId();
    }

    public PortfolioResponseDto getById(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new NoPortfolioFoundException());
        PortfolioResponseDto portfolioResponseDto = PortfolioResponseDto.createPortfolioResponseDto(portfolio);
        return portfolioResponseDto;
    }
}
