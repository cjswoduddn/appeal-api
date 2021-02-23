package com.appeal.api.member.service;

import com.appeal.api.common.dto.SignUpDto;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.exception.IllegalEmailValidAccessExcetion;
import com.appeal.exception.NoUserFoundException;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
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

    public void validSignUp(String code) {
        String memberId = Optional.ofNullable(redisTemplate.opsForValue().get(code))
                .orElseThrow(() -> new IllegalEmailValidAccessExcetion());

        memberRepository
                .findById(Long.parseLong(memberId))
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
