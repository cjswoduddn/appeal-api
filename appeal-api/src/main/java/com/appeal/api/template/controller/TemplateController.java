package com.appeal.api.template.controller;

import com.appeal.api.member.dto.AuthenticatedMember;
import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.template.dto.TemplateDto;
import com.appeal.api.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
public class TemplateController<D extends TemplateDto, S extends TemplateService>  {

    private final S templateService;

    /**
     *  dto변환 시 setter는 필수 중의 필수
     */
    @PostMapping
    public ResponseEntity<Long> createTemplate(@AuthenticatedMember MemberSession session, @Valid  D dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(templateService.createTemplate(session, dto))
                ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateDto> getTemplateById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(templateService.getTemplateById(id))
                ;
    }
}
