package com.appeal.api.security.sevice;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private final MemberSession member;

    /**
     *  상속한 User에는 기본적으로 username과 password 필드가 있고 getter() 또한 내장돼 있다.
     */
    public MemberContext(MemberSession member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPassword(), authorities);
        this.member = member;
    }

    public MemberSession getMember() {
        return member;
    }
}
