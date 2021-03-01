package com.appeal.api.common.dto.portfolio;


import lombok.AllArgsConstructor;
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
