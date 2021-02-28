package com.appeal.api.common.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PortfolioFileDto {
    private MultipartFile thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
