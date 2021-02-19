package com.appeal.api.portfolio.domain;

import com.appeal.api.portfolio.domain.Portfolio;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @DiscriminatorValue("YOUNG_WOO_TEMPLATE_ONE") @Getter
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
}
