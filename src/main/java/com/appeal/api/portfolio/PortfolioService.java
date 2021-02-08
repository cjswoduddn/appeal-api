package com.appeal.api.portfolio;

import com.appeal.api.member.Member;
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
}
