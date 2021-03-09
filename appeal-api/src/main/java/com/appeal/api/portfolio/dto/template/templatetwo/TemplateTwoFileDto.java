package com.appeal.api.portfolio.dto.template.templatetwo;

import com.appeal.api.portfolio.dto.portfolio.PortfolioFileDto;
import com.appeal.api.portfolio.dto.template.TemplateDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TemplateTwoFileDto implements TemplateDto {

    @NotNull(message = "이 값이 공백일 순 없습니다")
    private PortfolioFileDto portfolio;
    private List<TemplateTwoProjectFileDto> projects = new ArrayList<>();
    private List<TemplateTwoCareerDto> careers = new ArrayList<>();
}
