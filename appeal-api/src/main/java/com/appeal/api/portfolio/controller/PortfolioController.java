package com.appeal.api.portfolio.controller;

import com.appeal.api.member.dto.AuthenticatedMember;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.portfolio.dto.portfolio.PortfolioDto;
import com.appeal.api.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    /**
     * 사용자가 마이페이지를 통해 자신이 작성한 포트폴리오를 보는 경우
     */
    @GetMapping
    public ResponseEntity<List<PortfolioDto>> getMyPortfolio(@AuthenticatedMember MemberSession session){
        return ResponseEntity
                .ok()
                .body(portfolioService.getBySession(session))
                ;
    }

}