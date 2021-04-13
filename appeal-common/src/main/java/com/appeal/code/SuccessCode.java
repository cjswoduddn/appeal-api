package com.appeal.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum SuccessCode {
    // member
    CREATE_MEMBER("SM001", "멤버등록에 성공했습니다!", 201),
    VALID_MEMBER("SM002", "이메일 인증에 성공했습니다!", 200),
    SUCCESS_GET_MY_INFOMATION("SM003", "개인정보를 가져왔습니다!", 200),
    SUCCESS_UPDATE_MEMBER("SM004", "개인정보를 변경했습니다!", 200),

    // portfolio
    SUCCESS_GET_MY_PORTFOLIO("SP001", "내가 작성한 포트폴리오를 가져왔습니다!", 200),
    SUCCESS_GET_PORTFOLIO_BY_KEYWORD("SP002", "검색한 포트폴리오를 가져왔습니다!", 200),
    ;
    private final String code;
    private final String message;
    private final int status;
}
