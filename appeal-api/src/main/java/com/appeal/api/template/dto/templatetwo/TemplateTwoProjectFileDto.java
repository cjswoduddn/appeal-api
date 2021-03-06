package com.appeal.api.template.dto.templatetwo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class TemplateTwoProjectFileDto {

    private String name;
    private String intro;
    private String role;
    private MultipartFile thumbnail;
}
