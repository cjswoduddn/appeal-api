package com.appeal.api.member.dto;

import com.appeal.api.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class MemberSession implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String name;

    public static MemberSession createMemberSession(Member member){
        MemberSession memberSession = new MemberSession();
        memberSession.id = member.getId();
        memberSession.email = member.getEmail();
        memberSession.password = member.getPassword();
        return memberSession;
    }
}
