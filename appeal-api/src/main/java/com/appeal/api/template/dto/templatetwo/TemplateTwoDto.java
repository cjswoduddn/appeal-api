package com.appeal.api.template.dto.templatetwo;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import com.appeal.api.template.domain.templatetwo.TemplateTwoCareer;
import com.appeal.api.template.domain.templatetwo.TemplateTwoProject;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.exception.UnexpectedMethodArgumentNullPointerException;
import com.appeal.service.AwsS3Service;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TemplateTwoDto implements TemplateDto {
    private PortfolioDto portfolio;
    private List<TemplateTwoProjectDto> projects = new ArrayList<>();
    private List<TemplateTwoCareerDto> careers = new ArrayList<>();

    public static TemplateTwoDto convertFileDtoToStringUrl(TemplateTwoFileDto dto, AwsS3Service s3Service) {
        TemplateTwoDto templateTwoDto = new TemplateTwoDto();
        try {
            templateTwoDto.convertPortfolioFileDtoToStringUrlDto(dto, s3Service);
            templateTwoDto.convertProjectFileDtotoStringUrlDto(dto, s3Service);
            templateTwoDto.careers = dto.getCareers();  // can null
        }catch (NullPointerException e){
            throw new UnexpectedMethodArgumentNullPointerException("Null이 나올 수 없는 상황입니다");
        }
        return templateTwoDto;
    }

    // project에 대한, career에 대한 쿼리 두방이 호출될 것. 한 템플릿 쿼리를 위해 총 3방의 쿼리 호출
    public static TemplateTwoDto convertDomainToDto(TemplateTwo templateTwo) {
        TemplateTwoDto templateTwoDto = new TemplateTwoDto();
        templateTwoDto.convertPortfolioToPortfolioDto(templateTwo.getPortfolio());
        templateTwoDto.convertTemplateTwoProjectToTemplateTwoProjectDto(templateTwo.getProjects());
        templateTwoDto.convertTemplateTwoCareerToTemplateTwoCareerDto(templateTwo.getCareers());
        return templateTwoDto;
    }

    private void convertTemplateTwoCareerToTemplateTwoCareerDto(List<TemplateTwoCareer> careers){
        careers.forEach(career->{
            this.getCareers().add(TemplateTwoCareerDto.builder()
            .date(career.getDate())
            .title(career.getTitle())
            .intro(career.getIntro())
            .position(career.getPosition())
            .stack(career.getStack())
            .build())
            ;
        });
    }

    private void convertTemplateTwoProjectToTemplateTwoProjectDto(List<TemplateTwoProject> projects){
        projects.forEach(project->{
            this.getProjects().add(TemplateTwoProjectDto.builder()
                    .name(project.getName())
                    .role(project.getRole())
                    .intro(project.getIntro())
                   .thumbnail(project.getThumbnail())
                   .build()
            );
        });
    }
    private void convertPortfolioToPortfolioDto(Portfolio portfolio){
        this.portfolio = PortfolioDto.builder()
                .thumbnail(portfolio.getThumbnail())
                .intro(portfolio.getIntro())
                .name(portfolio.getName())
                .skill(portfolio.getSkill())
                .title(portfolio.getTitle())
                .build()
                ;
    }

    private void convertProjectFileDtotoStringUrlDto(TemplateTwoFileDto dto, AwsS3Service s3Service) {
        dto.getProjects().forEach(project -> {
            this.projects.add(new TemplateTwoProjectDto(
                    project.getName(),
                    project.getIntro(),
                    project.getRole(),
                    s3Service.upload(project.getThumbnail())
            ));
        });
    }

    private void convertPortfolioFileDtoToStringUrlDto(TemplateTwoFileDto dto, AwsS3Service s3Service) {
        this.portfolio =
        PortfolioDto.builder()
                .thumbnail(s3Service.upload(dto.getPortfolio().getThumbnail()))
                .intro(dto.getPortfolio().getIntro())
                .name(dto.getPortfolio().getName())
                .skill(dto.getPortfolio().getSkill())
                .title(dto.getPortfolio().getTitle())
                .build()
                ;
    }
}
