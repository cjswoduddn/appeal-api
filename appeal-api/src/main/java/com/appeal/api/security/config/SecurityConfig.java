package com.appeal.api.security.config;

import com.appeal.api.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(GET, "/member/**", "/template**/*", "/portfolio/**").permitAll()
                .antMatchers(POST, "/member", "/signin").permitAll()
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
        ;
        http
                .logout()
                .logoutUrl("/signout")
                .addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(200));

        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
        ;
        http
                .cors()
        ;
        http
                .exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler)
        ;

        http.addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    protected CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter(){
        CustomUsernamePasswordAuthenticationFilter authenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        try{
            authenticationFilter.setFilterProcessesUrl("/signin");
            authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
            authenticationFilter.setUsernameParameter("email");
            authenticationFilter.setPasswordParameter("password");
            authenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
            authenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationFilter;
    }
}