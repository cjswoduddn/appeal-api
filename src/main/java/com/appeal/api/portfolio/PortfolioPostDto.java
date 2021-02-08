package com.appeal.api.portfolio;

import com.appeal.api.common.exception.FailImageUploadException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * MultipartHttpServletRequest에서 getParameterMap이
 * <String, String[]>을 리턴하는 이유는 추측컨대
 * multimap을 지원하기 위함인 것 같다. 한 key에 여러개의 value를 담을 수 있게 하기 위함이다.
 * 추상화한 iteration을 사용한 현재 value 배열의 [0]과 같이 접근해도 문제 발생 소지는 없을 듯하다.
 */

public class PortfolioPostDto {

    private Map<String, String> map = new HashMap<>();
}
