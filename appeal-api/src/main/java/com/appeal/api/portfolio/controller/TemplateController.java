package com.appeal.api.portfolio.controller;

import com.appeal.api.common.dto.portfolio.TemplateDto;
import com.appeal.api.portfolio.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
public class TemplateController<D extends TemplateDto, S extends TemplateService>  {

    private final S templateService;

    /**
     *  dto변환 시 setter는 필수 중의 필수
     */
    @PostMapping
    public ResponseEntity<Long> createTemplate(@Valid  D dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(templateService.createTemplate(dto))
                ;
    }
}
