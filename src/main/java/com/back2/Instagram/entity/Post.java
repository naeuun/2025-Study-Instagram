package com.back2.Instagram.entity;

import com.back2.Instagram.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    // 게시물 내용 수정을 위한 메소드
    public void update(String newContent) {
        this.content = newContent;
    }
}

