package com.fiap.hackathon.user.app.adapter.input.web.dto;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserResponseTest {

	@Test
	void createUserResponseWithValidData() {
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", "12345678901");

		assertNotNull(userResponse);
		assertEquals(1L, userResponse.getId());
		assertEquals("John Doe", userResponse.getName());
		assertEquals("john.doe@example.com", userResponse.getEmail());
		assertEquals("12345678901", userResponse.getDocument());
	}

	@Test
	void createUserResponseWithNullName() {
		UserResponse userResponse = new UserResponse(1L, null, "john.doe@example.com", "12345678901");

		assertNotNull(userResponse);
		assertEquals(1L, userResponse.getId());
		assertNull(userResponse.getName());
		assertEquals("john.doe@example.com", userResponse.getEmail());
		assertEquals("12345678901", userResponse.getDocument());
	}

	@Test
	void createUserResponseWithEmptyEmail() {
		UserResponse userResponse = new UserResponse(1L, "John Doe", "", "12345678901");

		assertNotNull(userResponse);
		assertEquals(1L, userResponse.getId());
		assertEquals("John Doe", userResponse.getName());
		assertEquals("", userResponse.getEmail());
		assertEquals("12345678901", userResponse.getDocument());
	}

	@Test
	void createUserResponseWithNullDocument() {
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", null);

		assertNotNull(userResponse);
		assertEquals(1L, userResponse.getId());
		assertEquals("John Doe", userResponse.getName());
		assertEquals("john.doe@example.com", userResponse.getEmail());
		assertNull(userResponse.getDocument());
	}
}