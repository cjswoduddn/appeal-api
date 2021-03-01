package com.appeal.api.portfolio.service;

import com.appeal.api.common.dto.portfolio.TemplateDto;
import org.springframework.stereotype.Service;

public interface TemplateService {
    Long createTemplate(TemplateDto dto);
}
