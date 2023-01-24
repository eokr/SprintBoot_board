package com.practice.board.dto;

import com.practice.board.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer viewCount;
    private boolean deleteYesOrNo;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.viewCount = entity.getViewCount();
        this.deleteYesOrNo = entity.isDeleteYesOrNo();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
