package com.back2.Instagram.dto;

import com.back2.Instagram.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String content;
    private final String username;
    private final LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }
}
