package com.appeal.api.portfolio.service;

import com.appeal.api.portfolio.dto.TemplateDto;

public interface TemplateService {
    Long createTemplate(TemplateDto dto);

    TemplateDto getTemplateById(Long id);
}
