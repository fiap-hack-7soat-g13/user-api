package com.fiap.challenge.user.app.adapter.input.web.mapper;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserResponse;
import com.fiap.challenge.user.core.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toUserResponse(User user);

    List<UserResponse> toUser(List<User> users);

}
