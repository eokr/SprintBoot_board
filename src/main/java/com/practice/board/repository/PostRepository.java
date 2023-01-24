package com.practice.board.repository;

import com.practice.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);
}
