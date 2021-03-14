package com.appeal.api.template.controller;

import com.appeal.api.template.dto.templatetwo.TemplateTwoFileDto;
import com.appeal.api.template.service.TemplateTwoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/templatetwo")
public class TemplateTwoController extends TemplateController<TemplateTwoFileDto>{

    public TemplateTwoController(TemplateTwoService templateService) {
        super(templateService);
    }
}
