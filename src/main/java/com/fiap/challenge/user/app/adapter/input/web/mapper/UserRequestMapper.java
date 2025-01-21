package com.fiap.challenge.user.app.adapter.input.web.mapper;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import com.fiap.challenge.user.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    User toUser(UserRequest userRequest);

}
