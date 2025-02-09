package com.fiap.challenge.user.app.adapter.output.persistence.gateway;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import com.fiap.challenge.user.app.adapter.output.persistence.mapper.UserMapper;
import com.fiap.challenge.user.app.adapter.output.persistence.repository.UserRepository;
import com.fiap.challenge.user.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Transactional
    public User save(User user) {
        UserEntity userEntity = mapper.toUserEntity(user);
        UserEntity userSave = repository.save(userEntity);
        return mapper.toUser(userSave);
    }

    public User findById(Long id) {
        return repository.findById(id).map(mapper::toUser).orElse(null);
    }

    public List<User> findAll() {
        return mapper.toUser(repository.findAll());
    }

    @Transactional
    public List<User> findByDocument(String document) {
        return mapper.toUser(repository.findByDocument(document));
    }

    public List<User> findByEmail(String email) {
        return mapper.toUser(repository.findByEmail(email));
    }

    public void removeById(Long id) {
        UserEntity userEntity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(userEntity);
    }

}
