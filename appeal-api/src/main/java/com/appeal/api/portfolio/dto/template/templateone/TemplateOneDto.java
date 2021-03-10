package com.appeal.api.portfolio.dto.template.templateone;

import com.appeal.api.portfolio.dto.portfolio.PortfolioDto;
import com.appeal.service.AwsS3Service;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TemplateOneDto {
    private PortfolioDto portfolio;
    private List<TemplateOneCertificateDto> certificates = new ArrayList<>();
    private List<TemplateOneCareerDto> careers = new ArrayList<>();

    private String birth;
    private String englishName;
    private String phone;
    private String address;
    private String email;
    private String highschoolDate;
    private String highschoolName;
    private String highschoolMajor;
    private String highschoolGraduation;
    private String highschoolScroe;
    private String colleageDate;
    private String colleageName;
    private String colleageMajor;
    private String colleageScore;
    private String colleageGraduation;
    private String graduateschoolDate;
    private String graduateschoolName;
    private String graduateschoolMajor;
    private String graduateschoolGraduation;
    private String graduateschoolScore;
    private String militaryStatus;

    public static TemplateOneDto convertFileDtoToStringUrl(TemplateOneFileDto dto, AwsS3Service s3Service) {
        TemplateOneDto templateOneDto = new TemplateOneDto();
        templateOneDto.convertPortfolioFileDtoToStringUrlDto(dto, s3Service);
        templateOneDto.certificates = dto.getCertificates();
        templateOneDto.careers = dto.getCareers();

        templateOneDto.birth = dto.getBirth();
        templateOneDto.englishName = dto.getEnglishName();
        templateOneDto.phone = dto.getPhone();
        templateOneDto.address = dto.getAddress();
        templateOneDto.email = dto.getEmail();
        templateOneDto.highschoolDate = dto.getHighschoolDate();
        templateOneDto.highschoolName = dto.getHighschoolName();
        templateOneDto.highschoolMajor = dto.getHighschoolMajor();
        templateOneDto.highschoolGraduation = dto.getHighschoolGraduation();
        templateOneDto.highschoolScroe = dto.getHighschoolScore();
        templateOneDto.colleageDate = dto.getCollegeDate();
        templateOneDto.colleageName = dto.getCollegeName();
        templateOneDto.colleageMajor = dto.getCollegeMajor();
        templateOneDto.colleageScore = dto.getCollegeScore();
        templateOneDto.colleageGraduation = dto.getCollegeGraduation();
        templateOneDto.graduateschoolDate = dto.getGraduateschoolDate();
        templateOneDto.graduateschoolName = dto.getGraduateschoolName();
        templateOneDto.graduateschoolMajor = dto.getGraduateschoolMajor();
        templateOneDto.graduateschoolGraduation = dto.getGraduateschoolGraduation();
        templateOneDto.graduateschoolScore = dto.getGraduateschoolScore();
        templateOneDto.militaryStatus = dto.getMilitaryStatus();
        return templateOneDto;
    }
    private void convertPortfolioFileDtoToStringUrlDto(TemplateOneFileDto dto, AwsS3Service s3Service) {
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
