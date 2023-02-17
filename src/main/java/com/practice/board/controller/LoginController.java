package com.practice.board.controller;

import com.practice.board.dto.UserJoinDto;
import com.practice.board.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(UserJoinDto userJoinDto) {
        userServiceImpl.userJoinSave(userJoinDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
