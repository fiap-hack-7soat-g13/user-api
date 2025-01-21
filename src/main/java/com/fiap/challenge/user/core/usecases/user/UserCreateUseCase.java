package com.fiap.challenge.user.core.usecases.user;

import com.fiap.challenge.user.core.common.validator.UserCreateValidator;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCreateUseCase {

    private final UserGateway userGateway;
    private final UserCreateValidator validator;

    public User execute(User user) {
        validator.validate(user);
        return userGateway.save(user);
    }

}