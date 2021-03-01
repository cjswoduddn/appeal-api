package com.appeal.api.common.dto.portfolio;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class PortfolioFileDto {
    private MultipartFile thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;
}
