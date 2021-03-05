package com.appeal.api.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PortfolioDto {
    private String thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}