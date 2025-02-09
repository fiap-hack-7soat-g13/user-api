package com.fiap.hackathon.user.core.usecase.user;

import com.fiap.challenge.user.core.gateways.UserGateway;
import com.fiap.challenge.user.core.usecases.user.UserRemoveUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UserRemoveUseCaseTest {

	private UserGateway userGateway;
	private UserRemoveUseCase userRemoveUseCase;

	@BeforeEach
	void setUp() {
		userGateway = Mockito.mock(UserGateway.class);
		userRemoveUseCase = new UserRemoveUseCase(userGateway);
	}

	@Test
	void executeWithValidId() {
		userRemoveUseCase.execute(1L);

		verify(userGateway).removeById(1L);
	}

	@Test
	void executeWithNonExistentId() {
		doThrow(new IllegalArgumentException("User not found")).when(userGateway).removeById(1L);

		assertThrows(IllegalArgumentException.class, () -> userRemoveUseCase.execute(1L));
	}

}