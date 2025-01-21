package com.fiap.challenge.user.app.adapter.output.persistence.mapper;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import com.fiap.challenge.user.core.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);

    List<User> toUser(List<UserEntity> userEntities);

}
