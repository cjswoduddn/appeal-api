package com.appeal.api.template.controller;

import com.appeal.api.template.dto.templateone.TemplateOneFileDto;
import com.appeal.api.template.service.TemplateOneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateone")
public class TemplateOneController extends TemplateController<TemplateOneFileDto> {

    public TemplateOneController(TemplateOneService templateService) {
        super(templateService);
    }
}
