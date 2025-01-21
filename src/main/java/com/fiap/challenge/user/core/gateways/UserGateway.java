package com.fiap.challenge.user.core.gateways;

import com.fiap.challenge.user.core.domain.User;

import java.util.List;

public interface UserGateway {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    List<User> findByDocument(String document);

    List<User> findByEmail(String email);

    void removeById(Long id);

}
