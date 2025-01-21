package com.fiap.challenge.user.core.usecases.user;

import com.fiap.challenge.user.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserGetUseCase {

    private final UserGateway userGateway;

    public User execute(Long id) {
        User user = userGateway.findById(id);
        if (user == null)
            throw new EntityNotFoundException();

        return user;
    }

}