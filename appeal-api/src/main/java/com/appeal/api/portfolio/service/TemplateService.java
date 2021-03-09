package com.appeal.api.portfolio.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.portfolio.dto.TemplateDto;

public interface TemplateService {
    Long createTemplate(MemberSession session, TemplateDto dto);

    TemplateDto getTemplateById(Long id);
}
