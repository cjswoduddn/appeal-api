package com.appeal.api.common.dto.portfolio;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.util.PortfolioResponseDtoFactory;
import lombok.Getter;

@Getter
public abstract class PortfolioResponseDto {
    private String thumbnailUrl;
    private String title;
    private String skill;


    public static PortfolioResponseDto createPortfolioResponseDto(Portfolio portfolio) {
        PortfolioResponseDto portfolioResponseDto = PortfolioResponseDtoFactory.createPortfolioResponseDto(portfolio);
        portfolioResponseDto.thumbnailUrl = portfolio.getThumbnailUrl();
        portfolioResponseDto.title = portfolio.getTitle();
        portfolioResponseDto.skill = portfolio.getSkill();

        portfolioResponseDto.doInnerSetter(portfolio);
        return portfolioResponseDto;
    }

    protected abstract void doInnerSetter(Portfolio portfolio);
}
