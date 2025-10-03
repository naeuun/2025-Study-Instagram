package com.back2.Instagram.controller;

import com.back2.Instagram.entity.User;
import com.back2.Instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // 현재 로그인 유저 정보 주입
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // POST /api/posts/{postId}/like : 좋아요 추가/취소 (토글)
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal User currentUser // JWT 필터를 통해 SecurityContext에 저장된 User 객체 주입
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body("인증된 사용자만 좋아요를 누를 수 있습니다.");
        }

        boolean isLiked = likeService.toggleLike(postId, currentUser);

        if (isLiked) {
            return ResponseEntity.ok("좋아요가 추가되었습니다.");
        } else {
            return ResponseEntity.ok("좋아요가 취소되었습니다.");
        }
    }
}