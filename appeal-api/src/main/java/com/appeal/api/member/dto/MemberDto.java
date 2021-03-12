package com.appeal.api.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    @Email(message = "올바르지 않은 이메일 형식입니다") @NotBlank(message = "이메일을 입력하세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이름을 입력하세요")
    private String name;

    public static MemberDto createMemberDto(MemberSession session){
        MemberDto member = new MemberDto();
        member.email = session.getEmail();
        member.password = session.getPassword();
        member.name = session.getName();
        return member;
    }
}
