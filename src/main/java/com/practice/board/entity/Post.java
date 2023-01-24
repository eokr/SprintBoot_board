package com.practice.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert// jpa 문법에서 save 같이 insert 를 할때 null 인 필드를 제외시켜줌
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "view_cnt")
    private Integer viewCount;

    @Column(name = "notice_yn")
    private boolean noticeYesOrNo;

    @Column(name = "delete_yn")
    private boolean deleteYesOrNo;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Builder
    public Post(String title, String content, String writer, Integer viewCount, boolean noticeYesOrNo, boolean deleteYesOrNo) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.noticeYesOrNo = noticeYesOrNo;
        this.deleteYesOrNo = deleteYesOrNo;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }

}
