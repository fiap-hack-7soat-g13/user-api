package com.fiap.challenge.user.app.adapter.input.web.mapper;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import com.fiap.challenge.user.core.domain.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-18T15:53:35-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class UserRequestMapperImpl implements UserRequestMapper {

    @Override
    public User toUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequest.getName() );
        user.setEmail( userRequest.getEmail() );
        user.setDocument( userRequest.getDocument() );
        user.setPassword( userRequest.getPassword() );

        return user;
    }
}
