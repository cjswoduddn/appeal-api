package com.appeal.api.portfolio.service;

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

}
