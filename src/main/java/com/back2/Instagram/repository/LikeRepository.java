package com.back2.Instagram.repository;

import com.back2.Instagram.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
    long countByPostId(Long postId);
}