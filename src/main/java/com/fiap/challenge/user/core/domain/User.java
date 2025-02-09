package com.fiap.challenge.user.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String password;

}