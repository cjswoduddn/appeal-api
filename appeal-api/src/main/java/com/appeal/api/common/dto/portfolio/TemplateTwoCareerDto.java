package com.appeal.api.common.dto.portfolio;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class TemplateTwoCareerDto {
    private String title;
    private String date;
    private String position;
    private String stack;
    private String intro;
}
