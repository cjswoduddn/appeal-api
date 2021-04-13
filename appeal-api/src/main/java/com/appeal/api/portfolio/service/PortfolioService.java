package com.appeal.api.portfolio.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import com.appeal.code.ErrorCode;
import com.appeal.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final MemberRepository memberRepository;

    public List<PortfolioDto> getPortfolioBySession(MemberSession session) {
        return
                portfolioRepository.findByMember(memberRepository.findById(session.getId())
                        .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER)))
                        .stream().map(portfolio -> PortfolioDto.createPortfolioDto(portfolio))
                        .collect(Collectors.toList())
                ;
    }

    public List<PortfolioDto> getPortfolioByKeyword(String keyword) {
        return
                portfolioRepository.findByKeyword(keyword)
                        .stream().map(portfolio -> PortfolioDto.createPortfolioDto(portfolio))
                        .collect(Collectors.toList())
                ;
    }
}
