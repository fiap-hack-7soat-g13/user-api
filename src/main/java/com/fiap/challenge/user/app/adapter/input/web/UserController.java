package com.fiap.challenge.user.app.adapter.input.web;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import com.fiap.challenge.user.app.adapter.input.web.dto.UserResponse;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserRequestMapper;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserResponseMapper;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.usecases.user.UserCreateUseCase;
import com.fiap.challenge.user.core.usecases.user.UserGetUseCase;
import com.fiap.challenge.user.core.usecases.user.UserListUseCase;
import com.fiap.challenge.user.core.usecases.user.UserRemoveUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserCreateUseCase userCreateUseCase;
    private final UserGetUseCase userGetUseCase;
    private final UserRemoveUseCase userRemoveUseCase;
    private final UserListUseCase userListUseCase;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        User userSave = userCreateUseCase.execute(user);
        return userResponseMapper.toUserResponse(userSave);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        User user = userGetUseCase.execute(id);
        return userResponseMapper.toUserResponse(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        userRemoveUseCase.execute(id);
    }

    @GetMapping
    public List<UserResponse> list(@RequestParam(required = false) String document) {
        List<User> userList = userListUseCase.execute(document);
        return userResponseMapper.toUser(userList);
    }

}
