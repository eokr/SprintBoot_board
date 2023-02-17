package com.practice.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserJoinDto {

    private String userName;

    private String userNickName;

    private String password;
}
