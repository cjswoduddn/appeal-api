package com.appeal.api.common.dto.portfolio;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PortfolioFileDto {
    private MultipartFile thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
