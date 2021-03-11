package com.appeal.api.portfolio.dto.portfolio;

import com.appeal.api.portfolio.domain.Portfolio;
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

    public static PortfolioDto createPortfolioDto(Portfolio portfolio) {
        return PortfolioDto.builder()
                .intro(portfolio.getIntro())
                .name(portfolio.getName())
                .skill(portfolio.getSkill())
                .thumbnail(portfolio.getThumbnail())
                .intro(portfolio.getTitle())
                .build()
                ;
    }
}
