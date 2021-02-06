package com.appeal.api.member;

import com.appeal.api.common.exception.DuplicateEmailException;
import com.appeal.api.common.exception.IllegalEmailValidAccessExcetion;
import com.appeal.api.common.exception.NoUserFoundException;
import com.appeal.api.common.util.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;
    MailService mailService;
    PasswordEncoder passwordEncoder;
    Member member;

    String duplicateEmail = "duplicate";
    @BeforeEach
    void before(){
        memberRepository = mock(MemberRepository.class);
        mailService = mock(MailService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        memberService = new MemberService(memberRepository, mailService, passwordEncoder);
        member = mock(Member.class);
    }

    @Test
    @DisplayName("중복_이메일_방지")
    void signUpDuplicateFail(){
        // given
        SignUpDto dto = new SignUpDto();
        dto.setEmail(duplicateEmail);
        // when
        when(memberRepository.findByEmail(duplicateEmail)).thenReturn(Optional.ofNullable(new Member()));
        // then
        Assertions.assertThrows(DuplicateEmailException.class, ()->memberService.signUp(dto));
    }
    @Test
    @DisplayName("중복이_아닌_경우_성공")
    public void signUpDuplicateSuccess() throws Exception{
        //given
        SignUpDto dto = new SignUpDto();
        dto.setEmail(duplicateEmail);
        //when
        when(memberRepository.findByEmail("")).thenReturn(Optional.ofNullable(null));
        when(memberRepository.save(any())).thenReturn(member);
        when(member.getId()).thenReturn(any());
        //then
        memberService.signUp(dto);
    }

    @Test
    @DisplayName("유효코드_불일치_예외")
    public void NotMatchingVailCode() throws Exception{
        //given
        String code = "Hello";
        //when
        when(mailService.getId(code)).thenReturn(Optional.ofNullable(null));
        //then
        assertThrows(IllegalEmailValidAccessExcetion.class, () -> memberService.validSignUp(code));
    }
    @Test
    @DisplayName("인증시도_키값이_DB에_없는경우")
    public void NotFoundMemberValidEmail() throws Exception{
        //given
        String code = "hello";
        Optional<Long> id = Optional.ofNullable(1L);
        //when
        when(mailService.getId(code)).thenReturn(id);
        when(memberRepository.findById(id.get())).thenReturn(Optional.ofNullable(null));
        //then
        assertThrows(NoUserFoundException.class, ()->memberService.validSignUp(code));
    }
    @Test
    @DisplayName("인증_성공")
    public void successVaildCode() throws Exception{
    }

}