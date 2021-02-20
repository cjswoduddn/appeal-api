package com.appeal.api.portfolio.service;

import com.appeal.api.common.dto.portfolio.PortfolioResponseDto;
import com.appeal.api.common.exception.NoPortfolioFoundException;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    /**
     * portfolio는 객체를 생성할 메서드를 갖는다
     * 여기서 중요한 점은
     * dtype이 포함된 dto를 전달할 것 // 다만 이 dtype이란 것도 데이터 중심의 사고라는 점...
     * member를 전달할 것이다.
     * 이 외에 신경쓸 것이 있다면 잘못된 코드
     */
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
