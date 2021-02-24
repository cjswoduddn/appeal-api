package com.appeal.api.portfolio.domain;

import com.appeal.api.common.dto.portfolio.TemplateTwoCareerDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoCareer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORTFOLIO_ID")
    private TemplateTwo templateTwo;

    private String title;
    private String date;
    private String position;
    private String stack;
    private String intro;

    public static TemplateTwoCareer createTemplateTwoCareer(TemplateTwoCareerDto career, TemplateTwo templateTwo) {
        TemplateTwoCareer templateTwoCareer = new TemplateTwoCareer();
        templateTwoCareer.title = career.getTitle();
        templateTwoCareer.date = career.getDate();
        templateTwoCareer.position = career.getPosition();
        templateTwoCareer.stack = career.getStack();
        templateTwoCareer.intro = career.getIntro();
        templateTwoCareer.templateTwo = templateTwo;
        return templateTwoCareer;
    }
}
