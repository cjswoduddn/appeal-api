package com.appeal.api.security.sevice;

import com.appeal.api.member.Member;
import com.appeal.api.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Member> member = memberRepository.findByEmail(email);

        if(member == null || member.size() == 0)
            throw new UsernameNotFoundException("NO USER");


        Member getMember = member.get(0);
        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(getMember.getAuthority().name()));
        MemberContext memberContext = new MemberContext(member.get(0), roles);

        return memberContext;
    }
}
