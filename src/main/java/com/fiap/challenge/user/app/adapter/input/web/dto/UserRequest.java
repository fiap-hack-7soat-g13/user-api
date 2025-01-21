package com.fiap.challenge.user.app.adapter.input.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String email;
    private String document;
    private String password;

}