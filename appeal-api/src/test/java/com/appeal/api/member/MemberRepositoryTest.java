package com.appeal.api.member;

import com.appeal.api.common.Authority;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.domain.Member;
import com.appeal.api.member.repository.MemberRepository;
import com.appeal.exception.notfound.NotFoundMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    Member member1;

    @BeforeEach
    void before(){
        member1 = makeMember("ma@kakao.com", "youngwoo", "cjswo");
    }

    @Test
    @DisplayName("저장성공테스트")
    public void saveSucces() throws Exception{
        //given
        memberRepository.save(member1);

        //when
        Member member = memberRepository.findByEmail(member1.getEmail())
                .orElseThrow(()-> new NotFoundMemberException("fjfj"));

        //then
        assertNotNull(member.getId());
        assertEquals(member.getAuthority(), Authority.ROLE_PRE);
        validSameMember(member, member1);
    }

    @Test
    @DisplayName("중복저장안돼요")
    public void validDupication() throws Exception{
        //given
        memberRepository.save(member1);
        Member member = makeMember(member1.getEmail(), "ff", "ff");
        //when
        //then
        assertThrows(RuntimeException.class, ()->memberRepository.save(member));
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
    private void validSameMember(Member member, Member member1) {
        assertEquals(member.getName(), member1.getName());
        assertEquals(member.getEmail(), member1.getEmail());
        assertEquals(member.getPassword(), member1.getPassword());
    }

}