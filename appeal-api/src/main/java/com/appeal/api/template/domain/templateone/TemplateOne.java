package com.appeal.api.template.domain.templateone;

import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.template.dto.templateone.TemplateOneDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateOne {
    @Id
    @Column(name = "TEMPLATE_ID")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "templateOne", cascade = CascadeType.ALL)
    private List<TemplateOneCertificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "templateOne", cascade = CascadeType.ALL)
    private List<TemplateOneCareer> careers = new ArrayList<>();

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


    private static String getTemplateType() {
        return "templateone";
    }

    public static TemplateOne createTemplateOne(TemplateOneDto templateOneDto, Member member) {
        TemplateOne templateOne = new TemplateOne();
        templateOne.portfolio = Portfolio.createPortfolio(templateOneDto.getPortfolio(), getTemplateType(), member);
        templateOneDto.getCertificates().forEach(certificate->{
            templateOne.certificates.add(TemplateOneCertificate.createTemplateOneCertificate(certificate, templateOne));
        });
        templateOneDto.getCareers().forEach(career->{
            templateOne.careers.add(TemplateOneCareer.createTemplateOneCareer(career, templateOne));
        });
        templateOne.birth = templateOneDto.getBirth();
        templateOne.englishName = templateOneDto.getEnglishName();
        templateOne.phone = templateOneDto.getPhone();
        templateOne.address = templateOneDto.getAddress();
        templateOne.email = templateOneDto.getEmail();
        templateOne.highschoolDate = templateOneDto.getHighschoolDate();
        templateOne.highschoolName = templateOneDto.getHighschoolName();
        templateOne.highschoolMajor = templateOneDto.getHighschoolMajor();
        templateOne.highschoolGraduation = templateOneDto.getHighschoolGraduation();
        templateOne.highschoolScroe = templateOneDto.getHighschoolScroe();
        templateOne.colleageDate = templateOneDto.getColleageDate();
        templateOne.colleageName = templateOneDto.getColleageName();
        templateOne.colleageMajor = templateOneDto.getColleageMajor();
        templateOne.colleageScore = templateOneDto.getColleageScore();
        templateOne.colleageGraduation = templateOneDto.getColleageGraduation();
        templateOne.graduateschoolDate = templateOneDto.getGraduateschoolDate();
        templateOne.graduateschoolName = templateOneDto.getGraduateschoolName();
        templateOne.graduateschoolMajor = templateOneDto.getGraduateschoolMajor();
        templateOne.graduateschoolGraduation = templateOneDto.getGraduateschoolGraduation();
        templateOne.graduateschoolScore = templateOneDto.getGraduateschoolScore();
        templateOne.militaryStatus = templateOneDto.getMilitaryStatus();
        return templateOne;
    }
}
