package com.appeal.api.portfolio.controller;

import com.appeal.api.member.dto.AuthenticatedMember;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.portfolio.service.PortfolioService;
import com.appeal.code.SuccessCode;
import com.appeal.dto.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<List<PortfolioDto>> getMyPortfolio(@AuthenticatedMember MemberSession session) {
        return new ResponseEntity<>
                (
                        portfolioService.getPortfolioBySession(session)
                        , HttpStatus.OK
                )
                ;
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<PortfolioDto>> getPortfolioByKeyword(@PathVariable("keyword") String keyword) {
        return new ResponseEntity<>
                (
                        portfolioService.getPortfolioByKeyword(keyword),
                        HttpStatus.OK
                )
                ;
    }
}