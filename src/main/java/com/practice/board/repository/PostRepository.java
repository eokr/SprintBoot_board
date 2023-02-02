package com.practice.board.repository;

import com.practice.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);

    @Modifying
    @Query("update Post set viewCount = viewCount + 1 where id = :idPost")
    int updatePlusOneViewCount(Long idPost);
}