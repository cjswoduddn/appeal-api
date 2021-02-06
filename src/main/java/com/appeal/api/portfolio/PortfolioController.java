package com.appeal.api.portfolio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController @RequiredArgsConstructor
public class PortfolioController {


    @PostMapping("/portfolio")
    public ResponseEntity post(MultipartHttpServletRequest dto){
        return ResponseEntity.ok()
                .body("포스팅 완료!");
    }
}
