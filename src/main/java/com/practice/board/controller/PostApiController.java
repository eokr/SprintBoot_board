package com.practice.board.controller;

import com.practice.board.dto.PostRequestDto;
import com.practice.board.dto.PostResponseDto;
import com.practice.board.exception.CustomException;
import com.practice.board.exception.ErrorCode;
import com.practice.board.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostServiceImpl postService;

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }

    /**
     * 게시글 생성
     */
    @PostMapping("/posts")
    public Long save(@RequestBody final PostRequestDto params) {
        return postService.save(params);
    }

    /**
     * 게시글 리스트 조회
     */
    @GetMapping("/posts")
    public List<PostResponseDto> findAll() {
        return postService.findAll();
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/posts/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final PostRequestDto params) {
        return postService.update(id, params);
    }
}
