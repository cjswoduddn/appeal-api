package com.appeal.api.common.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class TemplateTwoFileDto {

    private PortfolioFileDto portfolio;
    private List<TemplateTwoProjectFileDto> projects;
    private List<TemplateTwoCareerDto> careers;
}
