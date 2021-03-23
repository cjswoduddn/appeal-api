package com.appeal.api.template.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.template.dto.TemplateDto;

public interface TemplateService {
    Long createTemplate(MemberSession session, TemplateDto dto);

    TemplateDto getTemplateById(Long id);

    void deleteTemplate(MemberSession session, Long id);
}
