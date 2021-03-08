package com.appeal.api.security.provider;

import com.appeal.api.common.Authority;
import com.appeal.exception.NoValidAccountException;
import com.appeal.api.security.sevice.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        MemberContext memberContext = (MemberContext)userDetailsService.loadUserByUsername(username);

        Object credentials = authentication.getCredentials();
        if(!passwordEncoder.matches((String)credentials, memberContext.getPassword()))
            throw new BadCredentialsException("password not matching!");

        SimpleGrantedAuthority rolePre = new SimpleGrantedAuthority(Authority.ROLE_PRE.name());
        if(memberContext.getAuthorities().contains(rolePre))
            throw new NoValidAccountException("미인증 계정입니다");


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                memberContext.getMember(), null, memberContext.getAuthorities()
        );
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
