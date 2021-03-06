package com.appeal.api.template.domain.templatetwo;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.template.dto.templatetwo.TemplateTwoDto;
import com.appeal.api.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateTwo {

    @Id
    @Column(name = "TEMPLATE_ID")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoProject> projects = new ArrayList<>();

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoCareer> careers = new ArrayList<>();

    public static TemplateTwo createTemplateTwo(TemplateTwoDto templateTwoDto, Member member) {
                TemplateTwo templateTwo = new TemplateTwo();
                templateTwo.portfolio = Portfolio.createPortfolio(templateTwoDto.getPortfolio(), getTemplateType(), member);
                templateTwoDto.getProjects().forEach(project->{
                    templateTwo.projects.add(TemplateTwoProject.createTemplateTwoProject(project, templateTwo));
                });
                templateTwoDto.getCareers().forEach(career->{
                    templateTwo.careers.add(TemplateTwoCareer.createTemplateTwoCareer(career, templateTwo));
        });
        return templateTwo;
    }

    private static String getTemplateType(){
        return "templatetwo";
    }
}
