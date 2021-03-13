package com.appeal.api.template.dto.templateone;

import com.appeal.api.portfolio.dto.PortfolioFileDto;
import com.appeal.api.template.dto.TemplateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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
    private String highschoolScore;
    private String collegeDate;
    private String collegeName;
    private String collegeMajor;
    private String collegeScore;
    private String collegeGraduation;
    private String graduateschoolDate;
    private String graduateschoolName;
    private String graduateschoolMajor;
    private String graduateschoolGraduation;
    private String graduateschoolScore;
    private String militaryStatus;
}
