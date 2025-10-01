package com.back2.Instagram.repository;

import com.back2.Instagram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<사용할 Entity, Entity의 ID 타입>
public interface PostRepository extends JpaRepository<Post, Long> {
    // 일단은 비워둡니다. JpaRepository가 기본적인 CRUD 메소드를 모두 제공해줍니다!
    // (save(), findById(), findAll(), delete() 등)
}