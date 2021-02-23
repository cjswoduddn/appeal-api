package com.appeal.api.common.dto.portfolio;


import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.YoungWooTemplateOne;
import lombok.Getter;

@Getter
public class YoungWooTemplateOneResponseDto extends PortfolioResponseDto{

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

    @Override
    protected void doInnerSetter(Portfolio portfolio) {
        name = ((YoungWooTemplateOne)portfolio).getName();
        intro = ((YoungWooTemplateOne)portfolio).getIntro();

        projectItem1Name = ((YoungWooTemplateOne)portfolio).getProjectItem1Name();
        projectItem1Role = ((YoungWooTemplateOne)portfolio).getProjectItem1Role();
        projectItem1Intro = ((YoungWooTemplateOne)portfolio).getProjectItem1Intro();
        projectItem1Thumbnail = ((YoungWooTemplateOne)portfolio).getProjectItem1Thumbnail();

        projectItem2Name = ((YoungWooTemplateOne)portfolio).getProjectItem2Name();
        projectItem2Role = ((YoungWooTemplateOne)portfolio).getProjectItem2Role();
        projectItem2Intro = ((YoungWooTemplateOne)portfolio).getProjectItem2Intro();
        projectItem2Thumbnail = ((YoungWooTemplateOne)portfolio).getProjectItem2Thumbnail();

        careerItem1Title = ((YoungWooTemplateOne)portfolio).getCareerItem1Title();
        careerItem1Date = ((YoungWooTemplateOne)portfolio).getCareerItem1Date();
        careerItem1Position = ((YoungWooTemplateOne)portfolio).getCareerItem1Position();
        careerItem1Stack = ((YoungWooTemplateOne)portfolio).getCareerItem1Stack();
        careerItem1Intro = ((YoungWooTemplateOne)portfolio).getCareerItem1Intro();

        careerItem2Title = ((YoungWooTemplateOne)portfolio).getCareerItem2Title();
        careerItem2Date = ((YoungWooTemplateOne)portfolio).getCareerItem2Date();
        careerItem2Position = ((YoungWooTemplateOne)portfolio).getCareerItem2Position();
        careerItem2Stack = ((YoungWooTemplateOne)portfolio).getCareerItem2Stack();
        careerItem2Intro = ((YoungWooTemplateOne)portfolio).getCareerItem2Intro();
    }
}
