package com.appeal.api.common.dto.portfolio;

import lombok.Data;

import java.util.List;

@Data
public class TemplateTwoFileDto {

    private PortfolioFileDto portfolio;
    private List<TemplateTwoProjectFileDto> projects;
    private List<TemplateTwoCareerDto> careers;
}
