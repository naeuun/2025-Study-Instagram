package com.back2.Instagram.repository;

import com.back2.Instagram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // createdAt 필드를 기준으로 모든 게시물을 내림차순으로 정렬하여 조회합니다.
    List<Post> findAllByOrderByCreatedAtDesc();
}