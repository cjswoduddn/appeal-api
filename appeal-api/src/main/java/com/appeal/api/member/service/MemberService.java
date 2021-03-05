package com.appeal.api.member.service;

import com.appeal.api.member.dto.SignUpDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.exception.IllegalEmailValidAccessExcetion;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.notfound.NotFoundMemberException;
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

    public void signUp(SignUpDto dto) {
        validDuplicateEmail(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = memberRepository.save(Member.createMember(dto));
        String code = mailService.sendVaildCodeToMember(dto.getEmail());
        redisTemplate.opsForValue().set(code, Long.toString(member.getId()));
    }

    private void validDuplicateEmail(SignUpDto dto) {
        memberRepository
                .findByEmail(dto.getEmail())
                .ifPresent(member -> {
                    throw new DuplicateEmailException(dto.getEmail()+"은(는) 이미 등록된 이메일입니당");
                });
    }


    public void validSignUp(String code) {
        String memberId = Optional.ofNullable(redisTemplate.opsForValue().get(code))
                .orElseThrow(() -> new IllegalEmailValidAccessExcetion());

        memberRepository
                .findById(Long.parseLong(memberId))
                .orElseThrow(()->new NotFoundMemberException("등록하지 않은 멤버입니다!"))
                .successEmailValid();
    }

    public void updateMemberInfo(Long id, UpdateMemberDto dto) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundMemberException("등록되지 않은 멤버입니다!");
                });
        member.updateInfo(dto);
    }
}
