package com.ssafy.buttonup.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//Spring Security를 사용하기 위해서는 Spring Security Filter Chain을 사용한다는 것을 명시해줘야함
@RequiredArgsConstructor
@EnableWebSecurity      //스프링 시큐리티 필터체인 사용한다는 것 명시
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    //암호화에 필요한 PasswordEncoder를 Bean에 등록
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //authenticationManager를 Bean에 등록
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().disable()  //rest api만을 고려하므로 기본설정 해제
                .csrf().disable()    //csrf 보안 토큰 disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     //토큰기반 인증이므로 세션 사용 x
                .and()
                .authorizeRequests()    //요청에 대한 사용권한 체크
                .antMatchers("/admin/**").hasRole("ADMIN")      //   "/admin/**", "/user/**" 형식의 url로 들어오는 요청에 대한 인증 요구
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()                //그 외 나머지 요청은 누구나 접근 가능
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                //JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter전에 넣는다.
    }
}
