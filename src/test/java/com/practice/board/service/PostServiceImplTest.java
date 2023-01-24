package com.practice.board.service;

import com.practice.board.entity.Post;
import com.practice.board.repository.PostRepository;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceImplTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void save() {

        // 포스트 파라미터 생성
        Post post = Post.builder()
                .title("1번 포스트 제목")
                .content("1번 포스트 내용")
                .writer("이준수")
                .viewCount(0)
                .noticeYesOrNo(false)
                .deleteYesOrNo(false)
                .build();

        // 포스트 저장
        postRepository.save(post);

        // 포스트 조회
        Post foundPost = postRepository.findById((long) 1).get();
        assertThat(foundPost.getTitle()).isEqualTo("1번 포스트 제목");
        assertThat(foundPost.getContent()).isEqualTo("1번 포스트 내용");
        assertThat(foundPost.getWriter()).isEqualTo("이준수");
    }

    @Test
    void findAll() {
        // 전체 포스트 수 조회
        long postCount = postRepository.count();

        // 전체 포스트 리스트 조회
        List<Post> posts = postRepository.findAll();
    }

    @Test
    void delete() {
        // 포스트 조회
        Post entity = postRepository.findById((long) 1).get();

        // 포스트 삭제
        postRepository.delete(entity);
    }
}