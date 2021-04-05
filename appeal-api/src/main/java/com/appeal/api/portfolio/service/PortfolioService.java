package com.appeal.api.portfolio.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import com.appeal.exception.ErrorCode;
import com.appeal.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final MemberRepository memberRepository;

    public List<PortfolioDto> getBySession(MemberSession session){
        Member member = memberRepository.findById(session.getId())
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
        List<Portfolio> portfolios = portfolioRepository.findByMember(member);
        List<PortfolioDto> dtos = new ArrayList<>();
        portfolios.forEach(portfolio -> {
            dtos.add(PortfolioDto.createPortfolioDto(portfolio));
        });
        return dtos;
    }

    public List<PortfolioDto> getPortfolioByKeyword(String keyword) {
        List<Portfolio> portfolios = portfolioRepository.findByKeyword(keyword);
        List<PortfolioDto> dtos = new ArrayList<>();
        portfolios.forEach(portfolio -> {
            dtos.add(PortfolioDto.createPortfolioDto(portfolio));
        });
        return dtos;
    }
}
