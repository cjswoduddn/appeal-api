package com.appeal.api.member.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.exception.FailValidEmailExcetion;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {


    MemberService memberService;
    MemberRepository memberRepository;
    MailService mailService;
    PasswordEncoder passwordEncoder;
    StringRedisTemplate redisTemplate;

    MemberDto dto;
    final String DUPLICATE = "DUPLICATE";


    @BeforeEach
    void before(){
        memberRepository = mock(MemberRepository.class);
        mailService = mock(MailService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        redisTemplate = mock(StringRedisTemplate.class);
        memberService = new MemberService(memberRepository, mailService, passwordEncoder, redisTemplate);
        dto = new MemberDto();
    }

    @Test
    @DisplayName("중복된 이메일 예외")
    public void signUpFailbyDuplicate() throws Exception{
        //given
        dto.setEmail(DUPLICATE);
        //when
        when(memberRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(mock(Member.class)));
        //then
        assertThrows(DuplicateEmailException.class, ()->memberService.signUp(dto));
    }

    @Test
    @DisplayName("레디스에서 코드에 맞는 멤버Id가 없는 경우")
    public void redisDoesNotHaveId() throws Exception{
        //given
        String code = "somecode";
        ValueOperations ops = mock(ValueOperations.class);
        //when
        when(redisTemplate.opsForValue()).thenReturn(ops);
        when(ops.get(code)).thenReturn(null);
        //then
        assertThrows(FailValidEmailExcetion.class, ()->memberService.validSignUp(code));
    }

    @Test
    @DisplayName("레디스에서 얻은 아이디가 DB에 등록이 안돼 있는 경우")
    public void fromRedisIdNotFoundDB() throws Exception{
        //given
        String code = "somecode";
        String id = "1";
        Long lid = 1L;
        ValueOperations ops = mock(ValueOperations.class);
        //when
        when(redisTemplate.opsForValue()).thenReturn(ops);
        when(ops.get(code)).thenReturn(id);
        when(memberRepository.findById(lid)).thenReturn(Optional.empty());
        //then
        assertThrows(NotFoundMemberException.class, ()->memberService.validSignUp(code));
    }

}