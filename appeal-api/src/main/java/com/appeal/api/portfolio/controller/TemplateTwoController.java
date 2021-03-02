package com.appeal.api.portfolio.controller;

import com.appeal.api.portfolio.dto.TemplateTwoFileDto;
import com.appeal.api.portfolio.service.TemplateTwoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/templatetwo")
public class TemplateTwoController extends TemplateController<TemplateTwoFileDto, TemplateTwoService> {

    public TemplateTwoController(TemplateTwoService templateService) {
        super(templateService);
    }
}
