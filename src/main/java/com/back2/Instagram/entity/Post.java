package com.back2.Instagram.entity; // 본인 프로젝트에 맞게 수정

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 이 클래스가 DB 테이블임을 나타냅니다.
@Entity
@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class Post {

    @Id // 기본 키(Primary Key)임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB가 ID를 자동으로 생성해줍니다.
    private Long id;

    @Column(nullable = false) // DB 컬럼이며, 비어있을 수 없음을 나타냅니다.
    private String content; // 게시물 내용

    // 나중에 User와 연관관계를 맺거나, 이미지 URL, 생성 시간 등을 추가하게 됩니다.
    // @ManyToOne
    // private User user;

    // 생성자: 게시물 내용을 받아 객체를 만듭니다.
    public Post(String content) {
        this.content = content;
    }
}