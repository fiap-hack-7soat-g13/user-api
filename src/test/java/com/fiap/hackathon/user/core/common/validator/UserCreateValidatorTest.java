package com.fiap.hackathon.user.core.common.validator;

import com.fiap.challenge.user.core.common.exception.InvalidDataException;
import com.fiap.challenge.user.core.common.validator.UserCreateValidator;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.gateways.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserCreateValidatorTest {

	private UserGateway userGateway;
	private UserCreateValidator userCreateValidator;

	@BeforeEach
	void setUp() {
		userGateway = Mockito.mock(UserGateway.class);
		userCreateValidator = new UserCreateValidator(userGateway);
	}

	@Test
	void validateValidUser() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "34242097085", "Password@123");
		when(userGateway.findByEmail(user.getEmail())).thenReturn(Collections.emptyList());
		when(userGateway.findByDocument(user.getDocument())).thenReturn(Collections.emptyList());

		userCreateValidator.validate(user);
	}

	@Test
	void validateUserWithBlankName() {
		User user = new User(1L, "", "john.doe@example.com", "12345678901", "Password@123");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithInvalidEmail() {
		User user = new User(1L, "John Doe", "invalid-email", "12345678901", "Password@123");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithExistingEmail() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.findByEmail(user.getEmail())).thenReturn(Collections.singletonList(user));

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithBlankDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "", "Password@123");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithInvalidDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "invalid-document", "Password@123");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithExistingDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		when(userGateway.findByDocument(user.getDocument())).thenReturn(Collections.singletonList(user));

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithBlankPassword() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithShortPassword() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "short");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}

	@Test
	void validateUserWithWeakPassword() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "weakpassword");

		assertThrows(InvalidDataException.class, () -> userCreateValidator.validate(user));
	}
}