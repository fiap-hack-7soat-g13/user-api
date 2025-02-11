package com.fiap.hackathon.user.core.usecase.user;

import com.fiap.challenge.user.core.common.exception.InvalidDataException;
import com.fiap.challenge.user.core.common.validator.UserCreateValidator;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import com.fiap.challenge.user.core.usecases.user.UserCreateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserCreateUseCaseTest {

	@Mock
	private UserGateway userGateway;
	@Mock
	private UserCreateValidator validator;
	private UserCreateUseCase userCreateUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
//		userGateway = Mockito.mock(UserGateway.class);
//		validator = Mockito.mock(UserCreateValidator.class);
		userCreateUseCase = new UserCreateUseCase(userGateway, validator);
	}

	@Test
	void executeWithValidUser() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.save(user)).thenReturn(user);

		User result = userCreateUseCase.execute(user);

		assertNotNull(result);
	}

	@Test
	void executeWithInvalidUser() {
		User user = new User(null, "john.doe@example.com", "12345678901", "0123456789", "Password@123");
		Mockito.doThrow(new InvalidDataException(List.of("Invalid user"))).when(validator).validate(user);

		assertThrows(InvalidDataException.class, () -> userCreateUseCase.execute(user));
	}

	@Test
	void executeWithExistingEmail() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		Mockito.doThrow(new InvalidDataException(List.of("Email already exists"))).when(validator).validate(user);

		assertThrows(InvalidDataException.class, () -> userCreateUseCase.execute(user));
	}

	@Test
	void executeWithExistingDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		Mockito.doThrow(new InvalidDataException(List.of("Document already exists"))).when(validator).validate(user);

		assertThrows(InvalidDataException.class, () -> userCreateUseCase.execute(user));
	}
}