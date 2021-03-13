package com.appeal.api.template.dto.templatetwo;

import com.appeal.api.portfolio.dto.PortfolioFileDto;
import com.appeal.api.template.dto.TemplateDto;
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
