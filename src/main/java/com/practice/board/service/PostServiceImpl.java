package com.practice.board.service;

import com.practice.board.dto.PostRequestDto;
import com.practice.board.dto.PostResponseDto;
import com.practice.board.entity.Post;
import com.practice.board.exception.CustomException;
import com.practice.board.exception.ErrorCode;
import com.practice.board.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final PostRepository postRepository;

    /**
     * 포스트 생성
     */
    @Transactional
    public Long save(final PostRequestDto params) {
        Post entity = postRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 포스트 리스트 조회
     */
    public List<PostResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Post> list = postRepository.findAll(sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 포스트 수정
     */
    @Transactional
    public Long update(final Long id, final PostRequestDto params) {

        Post entity = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }
}
