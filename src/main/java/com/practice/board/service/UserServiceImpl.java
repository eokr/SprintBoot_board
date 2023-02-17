package com.practice.board.service;

import com.practice.board.dto.UserJoinDto;
import com.practice.board.entity.User;
import com.practice.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    /**
     * 관리자 회원가입 정보 DB에 저장
     * @param userJoinDto
     * @return
     */
    public Long adminJoinSave(UserJoinDto userJoinDto) {
        String rawPassword = userJoinDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        User user = new User();
        user.setUserName(userJoinDto.getUserName());
        user.setUserNickName(userJoinDto.getUserNickName());
        user.setPassword(encPassword);
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);

        return user.getId();
    }

    /**
     * 유저 회원가입 정보 DB에 저장
     * @param userJoinDto
     * @return
     */
    public Long userJoinSave(UserJoinDto userJoinDto) {
        String rawPassword = userJoinDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        User user = new User();
        user.setUserName(userJoinDto.getUserName());
        user.setUserNickName(userJoinDto.getUserNickName());
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return user.getId();
    }
}
