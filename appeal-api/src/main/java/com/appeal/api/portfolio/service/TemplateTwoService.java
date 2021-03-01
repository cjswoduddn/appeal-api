package com.appeal.api.portfolio.service;

import com.appeal.api.common.dto.portfolio.TemplateDto;
import com.appeal.api.common.dto.portfolio.TemplateTwoDto;
import com.appeal.api.common.dto.portfolio.TemplateTwoFileDto;
import com.appeal.api.portfolio.domain.TemplateTwo;
import com.appeal.api.portfolio.repository.TemplateTwoRepository;
import com.appeal.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateTwoService implements TemplateService{

    private final AwsS3Service s3Service;   // 보기 너무 안 좋다
    private final TemplateTwoRepository templateTwoRepository;

    @Override
    public Long createTemplate(TemplateDto dto) {
        TemplateTwoDto templateTwoDto = TemplateTwoDto.convertFileDtoToStringUrl((TemplateTwoFileDto) dto, s3Service);
        TemplateTwo templateTwo = TemplateTwo.createTemplateTwo(templateTwoDto);
        templateTwoRepository.save(templateTwo);
        return templateTwo.getId();
    }
}
