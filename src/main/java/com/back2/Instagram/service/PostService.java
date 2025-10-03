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

    // 게시물 수정 로직
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        // 1. 게시물 조회 및 예외 처리
        Post post = findPost(postId);

        // 2. 작성자 본인 확인
        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물 수정 권한이 없습니다.");
        }

        // 3. 게시물 내용 수정
        post.update(requestDto.getContent());
        return new PostResponseDto(post);
    }

    // 게시물 삭제 로직
    @Transactional
    public void deletePost(Long postId, User user) {
        // 1. 게시물 조회 및 예외 처리
        Post post = findPost(postId);

        // 2. 작성자 본인 확인
        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("게시물 삭제 권한이 없습니다.");
        }

        // 3. 게시물 삭제
        postRepository.delete(post);
    }

    // 게시물 ID로 게시물을 찾아주는 private 메소드 (코드 중복 방지)
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}