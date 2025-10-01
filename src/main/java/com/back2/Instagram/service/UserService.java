package com.back2.Instagram.service;

import com.back2.Instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig 참조

    // public User signup(SignupRequest request){
    // }
    // public String login(LoginRequest request) {
    // }
}