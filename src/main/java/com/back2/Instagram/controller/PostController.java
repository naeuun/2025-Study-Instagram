package com.back2.Instagram.controller;

import com.back2.Instagram.dto.PostRequestDto;
import com.back2.Instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 REST API 컨트롤러임을 나타냅니다.
@RequestMapping("/api") // 공통 주소는 /api로 시작합니다.
@RequiredArgsConstructor
public class PostController {

    private final PostService postService; // 로직 처리를 위해 Service를 DI

    @PostMapping("/posts") // POST 방식으로 /api/posts 요청이 들어올 때
    public String createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
        return "게시물 작성이 완료되었습니다.";
    }
}