package com.appeal.api.portfolio.controller;

import com.appeal.api.portfolio.dto.template.templatetwo.TemplateTwoFileDto;
import com.appeal.api.portfolio.service.TemplateTwoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateone")
public class TemplateOneController extends TemplateController<TemplateTwoFileDto, TemplateTwoService> {
    public TemplateOneController(TemplateTwoService templateService) {
        super(templateService);
    }
}
