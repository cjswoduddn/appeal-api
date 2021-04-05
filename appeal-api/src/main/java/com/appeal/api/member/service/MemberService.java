package com.appeal.api.member.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.exception.ErrorCode;
import com.appeal.exception.FailValidEmailExcetion;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    public void signUp(MemberDto dto) {
        validDuplicateEmail(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = memberRepository.save(Member.createMember(dto));
        String code = mailService.sendVaildCodeToMember(dto.getEmail());
        redisTemplate.opsForValue().set(code, Long.toString(member.getId()));
    }

    private void validDuplicateEmail(MemberDto dto) {
        memberRepository
                .findByEmail(dto.getEmail())
                .ifPresent(member -> {
                    throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
                });
    }


    public void validSignUp(String code) {
        String memberId = Optional.ofNullable(redisTemplate.opsForValue().get(code))
                .orElseThrow(() -> new FailValidEmailExcetion(ErrorCode.FAIL_VALID_EMAIL));

        memberRepository
                .findById(Long.parseLong(memberId))
                .orElseThrow(()->new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER))
                .successEmailValid();
    }

    public void updateMemberInfo(MemberSession memberSession, UpdateMemberDto dto) {
        Member member = memberRepository
                .findById(memberSession.getId())
                .orElseThrow(() -> {
                    throw new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER);
                });
        member.updateInfo(dto);
    }

}
