package com.appeal.api.portfolio;

import com.appeal.api.common.exception.FailImageUploadException;
import com.appeal.api.common.util.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController @RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final AwsS3Service awsS3Service;


    @PostMapping("/portfolio")
    public ResponseEntity<Long> post(MultipartHttpServletRequest request){
        // 일단 맵으로 전환하는 과정에서 이미지 업로드까지 완료하긴 했는데 직관적이지 않고 예쁘지 않다. 추후 개선하자
        Map<String, String> map = convertRequestToMap(request);
        Long id = portfolioService.post(map);
        return ResponseEntity.ok()
                .body(id);
    }

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<Portfolio> get(@PathVariable("id") Long id){
        Portfolio portfolio = portfolioService.getById(id);
        return ResponseEntity.ok()
                .body(portfolio);
    }

    private Map<String, String> convertRequestToMap(MultipartHttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> map.put(key, value[0]));

        Map<String, MultipartFile> fileMap = request.getFileMap();
        try {
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                map.put(entry.getKey(), awsS3Service.upload(entry.getValue()));
            }
            return map;
        }catch (IOException e){
            throw new FailImageUploadException(e.getMessage());
        }
    }
}
