package com.back2.Instagram.service;

import com.back2.Instagram.domain.Role;
import com.back2.Instagram.domain.User;
import com.back2.Instagram.dto.LoginRequest;
import com.back2.Instagram.dto.SignupRequest;
import com.back2.Instagram.jwt.JwtTokenProvider;
import com.back2.Instagram.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 순환 참조 문제를 해결하기 위해 @RequiredArgsConstructor를 지우고 직접 생성자를 만듭니다.
    // PasswordEncoder 앞에 @Lazy를 붙여서 실제 사용될 때 주입되도록 합니다.
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Spring Security 인증
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "사용자를 찾을 수 없음. "));
    }

    // 회원가입
    @Transactional
    public User signup(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 있는 사용자 이름입니다.");
        }

        // dto를 Entity로
        User newUser = new User(
                null,
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()), // 비밀번호 암호화
                request.getEmail(),
                Role.USER
        );

        return userRepository.save(newUser);
    }

    // 로그인 - jwt 토큰
    @Transactional(readOnly = true)
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.generateToken(user.getUsername());
    }
}

