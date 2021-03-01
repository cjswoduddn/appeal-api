package com.appeal.api.common.dto.portfolio;

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
public class TemplateTwoDto {
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
