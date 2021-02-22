package com.appeal.api.portfolio.util;

import com.appeal.api.common.dto.portfolio.PortfolioResponseDto;
import com.appeal.api.common.dto.portfolio.YoungWooTemplateOneResponseDto;
import com.appeal.api.common.exception.NoSupportDtoException;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.YoungWooTemplateOne;

public class PortfolioResponseDtoFactory {

    public static PortfolioResponseDto createPortfolioResponseDto(Portfolio portfolio){
        if(portfolio instanceof YoungWooTemplateOne)
            return new YoungWooTemplateOneResponseDto();
        else
            throw new NoSupportDtoException(portfolio.toString());
    }

}
