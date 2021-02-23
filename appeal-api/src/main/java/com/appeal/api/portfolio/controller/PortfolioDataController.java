package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.PortfolioResponseDto;
import com.appeal.api.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
public class PortfolioDataController {

    private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioResponseDto> getOnePortfolio(@PathVariable("id") Long id){
        PortfolioResponseDto response = portfolioService.getById(id);
        return ResponseEntity.ok(
                response
        );
    }
}
