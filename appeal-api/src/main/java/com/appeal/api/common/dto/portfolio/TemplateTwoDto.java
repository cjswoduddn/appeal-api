package com.appeal.api.common.dto.portfolio;

import com.appeal.service.AwsS3Service;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TemplateTwoDto {
    private PortfolioDto portfolio;
    private List<TemplateTwoProjectDto> projects = new ArrayList<>();
    private List<TemplateTwoCareerDto> careers = new ArrayList<>();

    public static TemplateTwoDto convertFileDtoToStringUrl(TemplateTwoFileDto dto, AwsS3Service s3Service) {
        TemplateTwoDto templateTwoDto = new TemplateTwoDto();
        templateTwoDto.portfolio = new PortfolioDto(
                s3Service.upload(dto.getPortfolio().getThumbnail()),
                dto.getPortfolio().getTitle(),
                dto.getPortfolio().getSkill(),
                dto.getPortfolio().getName(),
                dto.getPortfolio().getIntro()
        );
        dto.getProjects().forEach(project -> {
            templateTwoDto.projects.add(new TemplateTwoProjectDto(
                    project.getName(),
                    project.getIntro(),
                    project.getRole(),
                    s3Service.upload(project.getThumbnail())
            ));
        });
        templateTwoDto.careers = dto.getCareers();
        return templateTwoDto;
    }
}
