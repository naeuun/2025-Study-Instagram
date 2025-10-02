package com.back2.Instagram.service;

import com.back2.Instagram.dto.PostRequestDto;
import com.back2.Instagram.entity.Post;
import com.back2.Instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 이 클래스가 서비스 계층임을 나타냅니다.
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줍니다.
public class PostService {

    private final PostRepository postRepository; // DB와 연결하기 위해 DI

    // 게시물 생성 로직
    public void createPost(PostRequestDto requestDto) {
        // 1. DTO에서 게시물 내용을 가져옵니다.
        String content = requestDto.getContent();

        // 2. 새로운 Post 객체를 만들고 내용을 담습니다.
        Post post = new Post(content);

        // 3. Repository를 이용해 DB에 저장합니다.
        postRepository.save(post);
    }
}