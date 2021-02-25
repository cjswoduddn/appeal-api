package com.appeal.api.common.dto.portfolio;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data @Builder
public class TemplateTwoProjectFileDto {
    private String name;
    private String intro;
    private String role;
    private MultipartFile thumbnail;
}
