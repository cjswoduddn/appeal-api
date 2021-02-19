package com.appeal.api.member.service;

import com.appeal.api.common.dto.SignUpDto;
import com.appeal.api.common.exception.DuplicateEmailException;
import com.appeal.api.common.exception.IllegalEmailValidAccessExcetion;
import com.appeal.api.common.exception.NoUserFoundException;
import com.appeal.api.common.util.MailService;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto dto) {
        validDuplicateEmail(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = memberRepository.save(Member.createMember(dto));
        mailService.sendValidMail(dto.getEmail(), member.getId());
    }

    public void validSignUp(String code) {
        Long memberId =
                mailService
                .getId(code)
                .orElseThrow(() -> new IllegalEmailValidAccessExcetion());

        memberRepository
                .findById(memberId)
                .orElseThrow(()->new NoUserFoundException())
                .successEmailValid();
    }

    private void validDuplicateEmail(SignUpDto dto) {
        memberRepository
                .findByEmail(dto.getEmail())
                .ifPresent(user->{
                    throw new DuplicateEmailException(dto.getEmail()+"은(는) 이미 등록된 이메일입니당");
                });
    }

}
