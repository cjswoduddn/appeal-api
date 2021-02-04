package com.appeal.api.security.config;

import com.appeal.api.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.appeal.api.security.handler.CustomAuthenticationFailureHandler;
import com.appeal.api.security.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .antMatchers("/signup/**", "/signin").permitAll()
                .anyRequest().authenticated()
        ;
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}