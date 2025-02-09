package com.fiap.hackathon.user.app.adapter.output.persistence.gateway;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import com.fiap.challenge.user.app.adapter.output.persistence.gateway.UserGatewayImpl;
import com.fiap.challenge.user.app.adapter.output.persistence.mapper.UserMapper;
import com.fiap.challenge.user.app.adapter.output.persistence.repository.UserRepository;
import com.fiap.challenge.user.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.user.core.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserGatewayImplTest {

	private UserMapper mapper;
	private UserRepository repository;
	private UserGatewayImpl userGateway;

	@BeforeEach
	void setUp() {
		mapper = Mockito.mock(UserMapper.class);
		repository = Mockito.mock(UserRepository.class);
		userGateway = new UserGatewayImpl(mapper, repository);
	}

	@Test
	void saveWithValidUser() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserEntity userEntity = new UserEntity();
		when(mapper.toUserEntity(user)).thenReturn(userEntity);
		when(repository.save(userEntity)).thenReturn(userEntity);
		when(mapper.toUser(userEntity)).thenReturn(user);

		User result = userGateway.save(user);

		assertNotNull(result);
		assertEquals(user, result);
	}

	@Test
	void findByIdWithValidId() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserEntity userEntity = new UserEntity();
		when(repository.findById(1L)).thenReturn(Optional.of(userEntity));
		when(mapper.toUser(userEntity)).thenReturn(user);

		User result = userGateway.findById(1L);

		assertNotNull(result);
		assertEquals(user, result);
	}

	@Test
	void findByIdWithNonExistentId() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		User result = userGateway.findById(1L);

		assertNull(result);
	}

	@Test
	void findAllUsers() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserEntity userEntity = new UserEntity();
		when(repository.findAll()).thenReturn(Collections.singletonList(userEntity));
		when(mapper.toUser(Collections.singletonList(userEntity))).thenReturn(Collections.singletonList(user));

		List<User> result = userGateway.findAll();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(user, result.get(0));
	}

	@Test
	void findByDocumentWithValidDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserEntity userEntity = new UserEntity();
		when(repository.findByDocument("12345678901")).thenReturn(Collections.singletonList(userEntity));
		when(mapper.toUser(Collections.singletonList(userEntity))).thenReturn(Collections.singletonList(user));

		List<User> result = userGateway.findByDocument("12345678901");

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(user, result.get(0));
	}

	@Test
	void findByDocumentWithNonExistentDocument() {
		when(repository.findByDocument("nonexistent")).thenReturn(Collections.emptyList());

		List<User> result = userGateway.findByDocument("nonexistent");

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void removeByIdWithValidId() {
		UserEntity userEntity = new UserEntity();
		when(repository.findById(1L)).thenReturn(Optional.of(userEntity));

		userGateway.removeById(1L);

		verify(repository).delete(userEntity);
	}

	@Test
	void removeByIdWithNonExistentId() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> userGateway.removeById(1L));
	}
}