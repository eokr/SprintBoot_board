package com.practice.board.repository;

import com.practice.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);

    /**
     * @Query 어노테이션을 통해 DML(insert, update, delete) 문을 실행할 때는
     * 반드시 @Modifying 을 붙여야 한다.
     * clearAutomatically = true 는 jpql 실행 후 자동으로 영속성 컨텍스트를 비워줌
     * 그러므로 데이터베이스에서 가져온 모든 데이터가 영속성 컨텍스트에 저장되어 최신상태를 유지함.
     */
    @Modifying(clearAutomatically = true)
    @Query("update Post set viewCount = viewCount + 1 where id = :idPost")
    int updatePlusOneViewCount(Long idPost);

    @Query("select p from Post p")
    Page<Post> findPageByAll(Pageable pageable);

    /**
     * 테이블의 Count 값 가져옴
     */
    @Query("select count(id) from Post where deleteYesOrNo = :deleteYesOrNo")
    Long CountByDeleteYesOrNo(boolean deleteYesOrNo);

}