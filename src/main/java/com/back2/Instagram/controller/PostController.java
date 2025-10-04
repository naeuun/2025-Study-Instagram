package com.back2.Instagram.controller;

import com.back2.Instagram.entity.User;
import com.back2.Instagram.dto.PostRequestDto;
import com.back2.Instagram.dto.PostResponseDto;
import com.back2.Instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 생성 API 수정
    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal User user) {
        // @AuthenticationPrincipal 어노테이션 덕분에 현재 로그인한 사용자 정보를 바로 User 객체로 받을 수 있습니다.
        postService.createPost(requestDto, user);
        return "게시물 작성이 완료되었습니다.";
    }

    //  전체 게시물 조회 API
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // 게시물 수정 API
    @PutMapping("/posts/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal User user) {
        return postService.updatePost(postId, requestDto, user);
    }

    // 게시물 삭제 API
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        postService.deletePost(postId, user);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }

}
