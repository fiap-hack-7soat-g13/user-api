package com.fiap.challenge.user.app.adapter.output.persistence.mapper;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import com.fiap.challenge.user.core.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-18T15:53:35-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( user.getId() );
        userEntity.name( user.getName() );
        userEntity.email( user.getEmail() );
        userEntity.document( user.getDocument() );

        return userEntity.build();
    }

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setName( userEntity.getName() );
        user.setEmail( userEntity.getEmail() );
        user.setDocument( userEntity.getDocument() );

        return user;
    }

    @Override
    public List<User> toUser(List<UserEntity> userEntities) {
        if ( userEntities == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userEntities.size() );
        for ( UserEntity userEntity : userEntities ) {
            list.add( toUser( userEntity ) );
        }

        return list;
    }
}
