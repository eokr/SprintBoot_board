package com.practice.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Service;

@Service
@DynamicInsert// jpa 문법에서 save 같이 insert 를 할때 null 인 필드를 제외시켜줌
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userid")
    private String userName;

    @Column(name = "usernickname")
    private String userNickName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
