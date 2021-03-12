package com.appeal.api.member.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDtoTest {

    @Test
    @DisplayName("세션으로 올바른 디티오가 생성되는가?")
    void createMemberDto() {
        //given
        MemberSession memberSession = new MemberSession();
        memberSession.setEmail("email");
        memberSession.setName("name");
        memberSession.setPassword("password");
        MemberDto memberDto = MemberDto.createMemberDto(memberSession);
        //when
        //then
        assertEquals(memberDto.getEmail(), memberSession.getEmail());
        assertEquals(memberDto.getName(), memberSession.getName());
        assertEquals(memberDto.getPassword(), memberSession.getPassword());
    }
}