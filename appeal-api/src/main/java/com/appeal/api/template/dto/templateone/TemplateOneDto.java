package com.appeal.api.template.dto.templateone;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.template.domain.templateone.TemplateOne;
import com.appeal.api.template.domain.templateone.TemplateOneCareer;
import com.appeal.api.template.domain.templateone.TemplateOneCertificate;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.service.AwsS3Service;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Getter @Setter
public class TemplateOneDto implements TemplateDto {
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
    private String highschoolScore;
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
        templateOneDto.highschoolScore = dto.getHighschoolScore();
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

    public static TemplateOneDto convertDomainToDto(TemplateOne templateOne) {
        TemplateOneDto templateOneDto = new TemplateOneDto();
        copyProperties(templateOne, templateOneDto);
        templateOneDto.convertPortfolioToPortfolioDto(templateOne.getPortfolio());
        templateOneDto.convertCareerToCareerDto(templateOne.getCareers());
        templateOneDto.convertCertificateToCertificateDto(templateOne.getCertificates());
        return templateOneDto;
    }

    private void convertCertificateToCertificateDto(List<TemplateOneCertificate> certificates) {
        certificates.forEach(certificate->{
            this.certificates.add(TemplateOneCertificateDto.builder()
            .date(certificate.getDate())
            .title(certificate.getTitle())
            .origin(certificate.getOrigin())
            .build());
        });
    }

    private void convertCareerToCareerDto(List<TemplateOneCareer> careers) {
        careers.forEach(career->{
            this.careers.add(TemplateOneCareerDto.builder()
            .date(career.getDate())
            .name(career.getName())
            .title(career.getTitle())
            .position(career.getPosition())
            .build());
        });
    }

    private void convertPortfolioToPortfolioDto(Portfolio portfolio) {
        this.portfolio = PortfolioDto.builder()
                .intro(portfolio.getIntro())
                .name(portfolio.getName())
                .skill(portfolio.getSkill())
                .thumbnail(portfolio.getThumbnail())
                .title(portfolio.getTitle())
                .build()
                ;
    }

}
