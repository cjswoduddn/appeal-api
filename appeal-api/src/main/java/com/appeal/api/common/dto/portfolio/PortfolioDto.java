package com.appeal.api.common.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PortfolioDto {
    private String thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
