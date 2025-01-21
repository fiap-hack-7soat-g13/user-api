package com.fiap.challenge.user.core.usecases.user;

import com.fiap.challenge.user.core.gateways.UserGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRemoveUseCase {

    private final UserGateway userGateway;

    public void execute(Long id) {
        userGateway.removeById(id);
    }

}