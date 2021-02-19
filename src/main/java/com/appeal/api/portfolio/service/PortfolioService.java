package com.appeal.api.portfolio.service;

import com.appeal.api.common.exception.NoPortfolioFoundException;
import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service @RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public Long post(Map<String, String> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Portfolio portfolio = Portfolio.createPortfolio(map, (Member) authentication.getPrincipal());
        portfolioRepository.save(portfolio);
        return portfolio.getId();
    }

    public Portfolio getById(Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(()->new NoPortfolioFoundException());
    }
}
