package com.appeal.api.template.dto.templateone;

import com.appeal.api.portfolio.dto.PortfolioFileDto;
import com.appeal.api.redis.service.AwsS3Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemplateOneDtoTest {

    String uploadUrl = "url";
    @Test
    @DisplayName("파일을 전부 스트링 URL로 변환")
    public void convertFileToString() throws Exception{
        //given
        TemplateOneFileDto templateOneFileDto = new TemplateOneFileDto();
        templateOneFileDto.setAddress("address");
        templateOneFileDto.setBirth("birth");
        templateOneFileDto.setPortfolio(new PortfolioFileDto());
        AwsS3Service s3Service = mock(AwsS3Service.class);
        when(s3Service.upload(any())).thenReturn(uploadUrl);

        //when
        TemplateOneDto templateOneDto = TemplateOneDto.convertFileDtoToStringUrl(templateOneFileDto, s3Service);

        //then
        checkConvert(templateOneDto, templateOneFileDto);
    }

    private void checkConvert(TemplateOneDto templateOneDto, TemplateOneFileDto templateOneFileDto) {
        assertEquals(templateOneDto.getAddress(), templateOneFileDto.getAddress());
        assertEquals(templateOneDto.getBirth(), templateOneFileDto.getBirth());
        assertEquals(templateOneDto.getPortfolio().getThumbnail(), uploadUrl);
    }

}