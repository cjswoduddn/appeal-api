package com.appeal.api.portfolio.domain;

import com.appeal.api.common.dto.portfolio.TemplateTwoProjectDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoProject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORTFOLIO_ID")
    private TemplateTwo templateTwo;

    private String name;
    private String intro;
    private String role;
    private String thumbnail;

    public static TemplateTwoProject createTemplateTwoProject(TemplateTwoProjectDto project, TemplateTwo templateTwo) {
        TemplateTwoProject templateTwoProject = new TemplateTwoProject();
        templateTwoProject.name = project.getName();
        templateTwoProject.intro = project.getIntro();
        templateTwoProject.role = project.getRole();
        templateTwoProject.thumbnail = project.getThumbnail();
        templateTwoProject.templateTwo = templateTwo;
        return templateTwoProject;
    }
}
