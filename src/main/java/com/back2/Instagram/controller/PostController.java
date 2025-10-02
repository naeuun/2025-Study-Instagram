package com.back2.Instagram.controller;

import com.back2.Instagram.domain.User;
import com.back2.Instagram.dto.PostRequestDto;
import com.back2.Instagram.dto.PostResponseDto;
import com.back2.Instagram.service.PostService;
import lombok.RequiredArgsConstructor;
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


}
