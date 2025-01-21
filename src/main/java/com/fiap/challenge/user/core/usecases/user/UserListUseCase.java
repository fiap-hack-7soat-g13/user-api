package com.fiap.challenge.user.core.usecases.user;

import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserListUseCase {

    private final UserGateway userGateway;

    public List<User> execute(String document) {
        return StringUtils.isBlank(document) ? userGateway.findAll() : userGateway.findByDocument(document);
    }

}