package com.practice.board.controller;

import com.practice.board.dto.PaginationDto;
import com.practice.board.dto.PostRequestDto;
import com.practice.board.dto.PostResponseDto;
import com.practice.board.entity.User;
import com.practice.board.service.PostServiceImpl;
import com.practice.board.service.UserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String openPostList(@RequestParam(value = "page") String page, Model model) {
        int recodeSize = 1;// 한 페이지당 보일 갯수
        int pageSize = 3;// 페이지네이션 바에 표시할 숫자 갯수
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
                                 @AuthenticationPrincipal UserDetail userDetail,
                                 Model model) {
        // 조회수 증가(쿠키값을 보고 없으면 증가)
        postServiceImpl.updateViewCount(idPost, request, response);
        // idPost 에 맞는 페이지 가져오기
        PostResponseDto postResponseDto = postServiceImpl.findById(idPost);
        model.addAttribute("postResponseDto", postResponseDto);

        User user = userDetail.getUser();
        model.addAttribute("userNickName", user.getUserNickName());
        return "postDetail";
    }

    /**
     * 포스트 작성 페이지로 이동
     * @AuthenticationPrincipal 어노테이션과 UserDetails 를 구현한
     * UserDetail 을 객체로 로그인한 사용자 정보를 가져올 수 있다.
     */
    @GetMapping("/write")
    public String writePost(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        User user = userDetail.getUser();
        model.addAttribute("userNickName", user.getUserNickName());
        return "writePost";
    }

    /**
     * 포스트 작성 후 등록
     */
    @PostMapping("/write")
    public String submitWritePost(PostRequestDto postRequestDto){
        postServiceImpl.save(postRequestDto);
        return "redirect:/post/list?page=1";
    }

    /**
     * 포스트 수정 페이지로 이동
     * 작성 필요
     */
    @GetMapping("/edit")
    public String editPost() {

        return "editPost";
    }
}