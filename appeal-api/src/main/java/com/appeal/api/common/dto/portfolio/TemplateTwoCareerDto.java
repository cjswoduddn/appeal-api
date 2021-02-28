package com.appeal.api.common.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class TemplateTwoCareerDto {
    private String title;
    private String date;
    private String position;
    private String stack;
    private String intro;
}
