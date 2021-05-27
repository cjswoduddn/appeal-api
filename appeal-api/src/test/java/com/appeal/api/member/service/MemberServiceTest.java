package com.appeal.api.member.service;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.exception.FailValidEmailExcetion;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.service.MailService;
import com.appeal.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {


    MemberService memberService;
    MemberRepository memberRepository;
    MailService mailService;
    PasswordEncoder passwordEncoder;
    RedisService redisService;

    MemberDto dto;
    final String DUPLICATE = "DUPLICATE";


    @BeforeEach
    void before(){
        memberRepository = mock(MemberRepository.class);
        mailService = mock(MailService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        redisService = mock(RedisService.class);
        memberService = new MemberService(memberRepository, mailService, passwordEncoder, redisService);
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

}