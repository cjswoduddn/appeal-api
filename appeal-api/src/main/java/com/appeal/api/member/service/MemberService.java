package com.appeal.api.member.service;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.exception.DuplicateEmailException;
import com.appeal.code.ErrorCode;
import com.appeal.exception.FailValidEmailExcetion;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.NotFoundMemberException;
import com.appeal.api.redis.service.MailService;
import com.appeal.api.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    @Transactional
    public void signUp(MemberDto dto) {
        validDuplicateEmail(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = memberRepository.save(Member.createMember(dto));
        String code = getRandomString(20);
        redisService.saveCodeEmail(code, member.getEmail());
        mailService.sendVaildCodeToMember(member.getEmail(), code);
    }

    private void validDuplicateEmail(MemberDto dto) {
        memberRepository
                .findByEmail(dto.getEmail())
                .ifPresent(member -> {
                    throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
                });
    }


    public void validSignUp(String code) {
        String email = redisService.getEmail(code)
                .orElseThrow(() -> new FailValidEmailExcetion(ErrorCode.FAIL_VALID_EMAIL));

        Member member = memberRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER));
        member.successEmailValid();
        memberRepository.save(member);
    }

    @Transactional
    public void updateMemberInfo(MemberSession memberSession, UpdateMemberDto dto) {
        Member member = memberRepository
                .findById(memberSession.getId())
                .orElseThrow(() -> {
                    throw new NotFoundMemberException(ErrorCode.NOT_FOUND_MEMBER);
                });
        member.updateInfo(dto);
    }

    private String getRandomString( int length ){
        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
                'r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < length ; i++ ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        return sb.toString();
    }

}
