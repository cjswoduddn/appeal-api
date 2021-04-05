package com.appeal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum ErrorCode {
    /**
    - 형식 : (부정형)?(동사)(대상)+
     */

    // common
    NOT_AUTHORIZATION("C001", "자원에 접근 권한이 없습니다", 403),
    NOT_SUPPORT_DTO("C002", "지원하지 않는 입력 형식입니다", 400),
    UNEXPECTED_METHOD_ARGUMENT_NULLPOINTER("C003", "말도 안되게 NULL이 나왔습니다 확인 요망", 500),
    FAIL_SEND_EMAIL("C004", "이메일 전송에 실패했습니다. 메일서버 확인!", 500),

    // member
    NOT_FOUND_MEMBER("M001", "등록되지 않은 멤버입니다!", 400),
    DUPLICATE_EMAIL("M002", "이미 등록한 이메일입니다", 409),
    FAIL_VALID_EMAIL("M003", "인증코드 접근이 올바르지 않습니다", 400),
    NOT_VALID_ACCOUNT("M004", "이메일 인증이 완료되지 않았습니다", 400),

    // portfolio
    NOT_FOUND_PORTFOLIO("P001", "등록되지 않은 포트폴리오입니다!", 400),
    FAIL_UPLOAD_IMAGE("P002", "이미지 업로드에 실패했습니다!", 502),

    ;
    private final String code;
    private final String message;
    private final int status;

}
