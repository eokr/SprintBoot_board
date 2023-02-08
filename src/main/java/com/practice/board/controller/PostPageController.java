package com.practice.board.controller;

import com.practice.board.dto.PaginationDto;
import com.practice.board.dto.PostRequestDto;
import com.practice.board.dto.PostResponseDto;
import com.practice.board.service.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostPageController {

    private final PostServiceImpl postServiceImpl;

    /**
     * 포스트 리스트 페이지
     */
    @GetMapping("/list")
    public String openPostList(@RequestParam String page, Model model) {
        int recodeSize = 2;
        int pageSize = 3;
        Page<PostResponseDto> postResponseDtoList = postServiceImpl.findPageByAll(page, recodeSize);
        PaginationDto paginationDto = postServiceImpl.getPaginationDto(page, recodeSize, pageSize);

        model.addAttribute("paginationDto", paginationDto);
        model.addAttribute("postResponseDtoList", postResponseDtoList);
        return "list";
    }

    /**
     * 포스트 조회 페이지
     */
    @GetMapping("/postDetail")
    public String openPostDetail(@RequestParam("idPost") Long idPost,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) {
        // 조회수 증가(쿠키값을 보고 없으면 증가)
        postServiceImpl.updateViewCount(idPost, request, response);
        // idPost 에 맞는 페이지 가져오기
        PostResponseDto postResponseDto = postServiceImpl.findById(idPost);
        model.addAttribute("postResponseDto", postResponseDto);
        return "postDetail";
    }

    /**
     * 포스트 작성 페이지로 이동
     */
    @GetMapping("/write")
    public String writePost() {
        return "writePost";
    }

    /**
     * 포스트 작성 후 등록
     */
    @PostMapping("/write")
    public String submitWritePost(PostRequestDto postRequestDto){
        postServiceImpl.save(postRequestDto);
        return "redirect:/post/list";
    }
}