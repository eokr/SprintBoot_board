package com.practice.board.dto;

import com.practice.board.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String writer;
    private boolean deleteYesOrNo;

    public PostRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.deleteYesOrNo = false;
    }
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .viewCount(0)
                .deleteYesOrNo(deleteYesOrNo)
                .build();
    }
}