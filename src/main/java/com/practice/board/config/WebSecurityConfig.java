package com.practice.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()//CRRF 공격에 대한 방어 해제
                //URI에 따른 페이지에 대한 권한을 부여하기 위해 시작하는 메소드
                .authorizeHttpRequests()
                //특정 URL 접근 시 인가가 필요한 URI를 설정
                //.authenticated()가 붙으면 해당 URI는 인증이 필요한 URI
                //.hasAnyRole("역할") 사용자가 주어진 어떤 권한이라도 있으면 허용
                .requestMatchers("/post/**").authenticated()
                .requestMatchers("/admin/**").hasAnyRole("admin")
                //특정 URI을 제외한 나머지 URI는 전부 인가
                .anyRequest().permitAll()
                .and()
                //아이디와 비밀번호를 입력해서 들어오는 로그인 형태 지원하는 Spring Security 기능,
                //해당 로그인 방식 이용할 때 Spring Security가 제공하는 기능 이용할 수 있다.
                //formLogin()을 통해 아래 loginPage() 같은 메소드를 사용할 수 있다.
                .formLogin()
                //인가되지 않은 사용자에게 보여줄 페이지 설정
                //로그인 페이지로 redirect 하기 위한 메소드
                //
                .loginPage("/login")
                //로그인 성공했을 때 기본 url
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/")
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}