package com.appeal.api.common.dto.portfolio;

import com.appeal.exception.FailImageUploadException;
import com.appeal.service.AwsS3Service;
import lombok.Getter;

import java.io.IOException;

@Getter
public class YoungWooTemplateOneStringDto {
    private String thumbnailUrl;
    private String title;
    private String skill;

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

    public static YoungWooTemplateOneStringDto createDto(YoungWooTemplateOneFileDto dto, AwsS3Service awsS3Service) {
        YoungWooTemplateOneStringDto convertedDto = new YoungWooTemplateOneStringDto();
        try {
            convertedDto.thumbnailUrl = awsS3Service.upload(dto.getThumbnail());
            convertedDto.projectItem1Thumbnail = awsS3Service.upload(dto.getProjectItem1Thumbnail());
            convertedDto.projectItem2Thumbnail = awsS3Service.upload(dto.getProjectItem2Thumbnail());
        }catch (IOException e){
            throw new FailImageUploadException("이미지 업로드 실패!");
        }

        convertedDto.skill = dto.getSkill();
        convertedDto.title = dto.getTitle();
        convertedDto.name = dto.getName();
        convertedDto.intro = dto.getIntro();
        convertedDto.projectItem1Name = dto.getProjectItem1Name();
        convertedDto.projectItem1Role = dto.getProjectItem1Role();
        convertedDto.projectItem1Intro = dto.getProjectItem1Intro();
        convertedDto.projectItem2Name = dto.getProjectItem2Name();
        convertedDto.projectItem2Role = dto.getProjectItem2Role();
        convertedDto.projectItem2Intro = dto.getProjectItem2Intro();
        convertedDto.careerItem1Title = dto.getCareerItem1Title();
        convertedDto.careerItem1Date = dto.getCareerItem1Date();
        convertedDto.careerItem1Position = dto.getCareerItem1Position();
        convertedDto.careerItem1Stack = dto.getCareerItem1Stack();
        convertedDto.careerItem1Intro = dto.getCareerItem1Intro();
        convertedDto.careerItem2Title = dto.getCareerItem2Title();
        convertedDto.careerItem2Date = dto.getCareerItem2Date();
        convertedDto.careerItem2Position = dto.getCareerItem2Position();
        convertedDto.careerItem2Stack = dto.getCareerItem2Stack();
        convertedDto.careerItem2Intro = dto.getCareerItem2Intro();
        return convertedDto;
    }
}
