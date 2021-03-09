package com.appeal.api.portfolio.dto.template.templateone;

import com.appeal.api.portfolio.dto.portfolio.PortfolioFileDto;
import com.appeal.api.portfolio.dto.template.TemplateDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TemplateOneFileDto implements TemplateDto {
    @NotNull(message = "이 값이 공백일 순 없습니다")
    private PortfolioFileDto portfolio;
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
}
