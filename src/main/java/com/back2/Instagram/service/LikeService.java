package com.back2.Instagram.service;

import com.back2.Instagram.entity.User;
import com.back2.Instagram.entity.Like;
import com.back2.Instagram.repository.LikeRepository;
import com.back2.Instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository; // 게시물 존재 확인용

    @Transactional
    public boolean toggleLike(Long postId, User currentUser) {
        postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트"));

        return likeRepository.findByUserIdAndPostId(currentUser.getId(), postId)
                .map(like -> {
                    likeRepository.delete(like);
                    return false; // 좋아요 취소
                })
                .orElseGet(() -> {
                    Like newLike = new Like(currentUser, postRepository.getReferenceById(postId));
                    likeRepository.save(newLike);
                    return true;
                });
    }

    @Transactional(readOnly = true)
    public long getLikeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}