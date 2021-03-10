package com.appeal.api.portfolio.controller;

import com.appeal.api.portfolio.dto.template.templateone.TemplateOneFileDto;
import com.appeal.api.portfolio.service.TemplateTwoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateone")
public class TemplateOneController extends TemplateController<TemplateOneFileDto, TemplateTwoService> {
    public TemplateOneController(TemplateTwoService templateService) {
        super(templateService);
    }
}
