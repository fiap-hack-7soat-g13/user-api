package com.fiap.hackathon.user.core.usecase.user;

import com.fiap.challenge.user.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import com.fiap.challenge.user.core.usecases.user.UserGetUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserGetUseCaseTest {

	private UserGateway userGateway;
	private UserGetUseCase userGetUseCase;

	@BeforeEach
	void setUp() {
		userGateway = Mockito.mock(UserGateway.class);
		userGetUseCase = new UserGetUseCase(userGateway);
	}

	@Test
	void executeWithValidId() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.findById(1L)).thenReturn(user);

		User result = userGetUseCase.execute(1L);

		assertNotNull(result);
	}

	@Test
	void executeWithNonExistentId() {
		when(userGateway.findById(1L)).thenReturn(null);

		assertThrows(EntityNotFoundException.class, () -> userGetUseCase.execute(1L));
	}

}