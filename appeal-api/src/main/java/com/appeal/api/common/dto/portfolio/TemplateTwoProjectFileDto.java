package com.appeal.api.common.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TemplateTwoProjectFileDto {
    private String name;
    private String intro;
    private String role;
    private MultipartFile thumbnail;
}
