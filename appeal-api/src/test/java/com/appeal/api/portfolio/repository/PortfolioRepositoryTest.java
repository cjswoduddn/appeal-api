package com.appeal.api.portfolio.repository;

import com.appeal.api.member.domain.Member;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.api.portfolio.domain.Portfolio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PortfolioRepositoryTest {
    @Autowired PortfolioRepository repository;
    @Autowired
    MemberRepository memberRepository;

  

    private Portfolio makePortfolio(Member member){
        return Portfolio.builder()
                .member(member)
                .build()
                ;
    }

    private Member makeMember(String email, String name, String password){
        return
                Member.createMember(MemberDto.builder().
                        email(email).
                        name(name).
                        password(password)
                        .build()
                );
    }
}