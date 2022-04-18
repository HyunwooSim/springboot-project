package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //롬복 어노테이션_클래스 내 모든 필드의 getter 메소드 생성
@NoArgsConstructor //롬복 어노테이션_기본생성자 자동 추가
@Entity//JPA 어노테이션_테이블과 링크될 클래스-> Entity 클래스는 절때 setter 생성하지 않음
public class Posts extends BaseTimeEntity {
    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title,  String content){
        this.title = title;
        this.content = content;
    }

}
