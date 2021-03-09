package com.appeal.api.portfolio.dto.template.templatetwo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class TemplateTwoProjectDto {
    private String name;
    private String intro;
    private String role;
    private String thumbnail;
}
