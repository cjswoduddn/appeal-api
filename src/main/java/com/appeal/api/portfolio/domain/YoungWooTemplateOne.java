package com.appeal.api.portfolio.domain;

import com.appeal.api.common.dto.portfolio.YoungWooTemplateOneStringDto;
import com.appeal.api.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YoungWooTemplateOne extends Portfolio {

    //about me
    private String name;
    private String intro;

    //project
    private String projectItem1Name;
    private String projectItem1Role;
    private String projectItem1Intro;
    private String projectItem1Thumbnail;

    private String projectItem2Name;
    private String projectItem2Role;
    private String projectItem2Intro;
    private String projectItem2Thumbnail;

    //career
    private String careerItem1Title;
    private String careerItem1Date;
    private String careerItem1Position;
    private String careerItem1Stack;
    private String careerItem1Intro;

    private String careerItem2Title;
    private String careerItem2Date;
    private String careerItem2Position;
    private String careerItem2Stack;
    private String careerItem2Intro;

    public YoungWooTemplateOne(YoungWooTemplateOneStringDto convertedDto, Member member) {
        super(convertedDto, member);
    }

    public static Portfolio createPortfolio(YoungWooTemplateOneStringDto convertedDto, Member member) {
        YoungWooTemplateOne portfolio = new YoungWooTemplateOne(convertedDto, member);
        portfolio.name = convertedDto.getName();
        portfolio.intro = convertedDto.getIntro();
        portfolio.projectItem1Name = convertedDto.getProjectItem1Name();
        portfolio.projectItem1Role = convertedDto.getProjectItem1Role();
        portfolio.projectItem1Intro = convertedDto.getProjectItem1Intro();
        portfolio.projectItem1Thumbnail = convertedDto.getProjectItem1Thumbnail();
        portfolio.projectItem2Name = convertedDto.getProjectItem2Name();
        portfolio.projectItem2Role = convertedDto.getProjectItem2Role();
        portfolio.projectItem2Intro = convertedDto.getProjectItem2Intro();
        portfolio.projectItem2Thumbnail = convertedDto.getProjectItem2Thumbnail();
        portfolio.careerItem1Title = convertedDto.getCareerItem1Title();
        portfolio.careerItem1Date = convertedDto.getCareerItem1Date();
        portfolio.careerItem1Position = convertedDto.getCareerItem1Position();
        portfolio.careerItem1Stack = convertedDto.getCareerItem1Stack();
        portfolio.careerItem1Intro = convertedDto.getCareerItem1Intro();
        portfolio.careerItem2Title = convertedDto.getCareerItem2Title();
        portfolio.careerItem2Date = convertedDto.getCareerItem2Date();
        portfolio.careerItem2Position = convertedDto.getCareerItem2Position();
        portfolio.careerItem2Stack = convertedDto.getCareerItem2Stack();
        portfolio.careerItem2Intro = convertedDto.getCareerItem2Intro();
        return portfolio;
    }
}
