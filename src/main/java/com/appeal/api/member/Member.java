package com.appeal.api.member;

import com.appeal.api.common.Authority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * todo : extends BaseTimeInfo
 */
@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    /**
     * static create method
     */
    public static Member createMember(SignUpDto dto){
        Member member = new Member();
        member.name = dto.getName();
        member.password = dto.getPassword();
        member.email = dto.getEmail();
        member.authority = Authority.ROLE_PRE;
        return member;
    }

    public void successEmailValid() {
        authority = Authority.ROLE_USER;
    }
}
