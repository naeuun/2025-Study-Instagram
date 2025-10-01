package com.back2.Instagram.controller;

import com.back2.Instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // @PostMapping("/signup")
    // public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
    // }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // }
}