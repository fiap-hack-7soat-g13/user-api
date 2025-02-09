package com.fiap.hackathon.user.core.usecase.user;

import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import com.fiap.challenge.user.core.usecases.user.UserListUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserListUseCaseTest {

	private UserGateway userGateway;
	private UserListUseCase userListUseCase;

	@BeforeEach
	void setUp() {
		userGateway = Mockito.mock(UserGateway.class);
		userListUseCase = new UserListUseCase(userGateway);
	}

	@Test
	void executeWithBlankDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.findAll()).thenReturn(Collections.singletonList(user));

		List<User> result = userListUseCase.execute("");

		assertEquals(1, result.size());
		assertEquals(user, result.get(0));
	}

	@Test
	void executeWithValidDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.findByDocument("12345678901")).thenReturn(Collections.singletonList(user));

		List<User> result = userListUseCase.execute("12345678901");

		assertEquals(1, result.size());
		assertEquals(user, result.get(0));
	}

	@Test
	void executeWithNonExistentDocument() {
		when(userGateway.findByDocument("nonexistent")).thenReturn(Collections.emptyList());

		List<User> result = userListUseCase.execute("nonexistent");

		assertEquals(0, result.size());
	}
}