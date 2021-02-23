package com.appeal.api.common.dto.portfolio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter  @NoArgsConstructor
public class YoungWooTemplateOneFileDto {
    //portfolio
    private MultipartFile thumbnail;
    private String title;
    private String skill;

    //about me
    private String name;
    private String intro;

    //project
    private String projectItem1Name;
    private String projectItem1Role;
    private String projectItem1Intro;
    private MultipartFile projectItem1Thumbnail;

    private String projectItem2Name;
    private String projectItem2Role;
    private String projectItem2Intro;
    private MultipartFile projectItem2Thumbnail;

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
