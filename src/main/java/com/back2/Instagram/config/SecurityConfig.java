package com.back2.Instagram.config;

import com.back2.Instagram.jwt.JwtAuthenticationFilter;
import com.back2.Instagram.jwt.JwtTokenProvider;
import com.back2.Instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 요청별 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // (수정) 루트 경로와 index.html, /api/auth/** 경로는 인증 없이 접근 허용
                        .requestMatchers("/", "/index.html", "/api/auth/**").permitAll()
                        .anyRequest().authenticated()  // 나머지 URL은 인증 필요
                )
                // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userService), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}