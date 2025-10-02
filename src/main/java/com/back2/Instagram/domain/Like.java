package com.back2.Instagram.domain;

import com.back2.Instagram.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes") // DB 테이블명
@Getter
@Setter
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 좋아요 누른 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // likes 테이블의 user_id 컬럼
    private User user;

    // 좋아요 누른 포스트
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false) // likes 테이블의 post_id 컬럼
    private Post post;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}