package com.fiap.challenge.user.app.adapter.output.persistence.repository;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByDocument(String document);
    List<UserEntity> findByEmail(String email);

}
