package com.appeal.api.common.dto.portfolio;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data @Builder
public class PortfolioFileDto {
    private MultipartFile thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
