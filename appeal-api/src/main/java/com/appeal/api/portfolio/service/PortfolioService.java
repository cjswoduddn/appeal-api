package com.appeal.api.portfolio.service;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service @RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    /**
     * 추상화된 dto를 받았다고 생각해보자.
     * @param portfolio
     * @return
     */
    public Long savePortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio).getId();
    }

    public Portfolio findById(Long id){
        return portfolioRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
