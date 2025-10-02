package com.back2.Instagram.service;

import com.back2.Instagram.domain.User;
import com.back2.Instagram.dto.PostRequestDto;
import com.back2.Instagram.dto.PostResponseDto;
import com.back2.Instagram.entity.Post;
import com.back2.Instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시물 생성 로직: 데이터 변경이 있으므로 @Transactional 추가
    @Transactional
    public void createPost(PostRequestDto requestDto, User user) {
        String content = requestDto.getContent();
        Post post = new Post(content, user);
        postRepository.save(post);
    }

    // 전체 게시물 조회 로직: 읽기 전용이므로 @Transactional(readOnly = true) 추가
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}