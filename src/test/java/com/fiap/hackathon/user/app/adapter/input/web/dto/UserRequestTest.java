package com.fiap.hackathon.user.app.adapter.input.web.dto;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestTest {

	@Test
	void createUserRequestWithValidData() {
		UserRequest userRequest = new UserRequest("John Doe", "john.doe@example.com", "12345678901", "Password@123");

		assertNotNull(userRequest);
		assertEquals("John Doe", userRequest.getName());
		assertEquals("john.doe@example.com", userRequest.getEmail());
		assertEquals("12345678901", userRequest.getDocument());
		assertEquals("Password@123", userRequest.getPassword());
	}

	@Test
	void createUserRequestWithNullName() {
		UserRequest userRequest = new UserRequest(null, "john.doe@example.com", "12345678901", "Password@123");

		assertNotNull(userRequest);
		assertNull(userRequest.getName());
		assertEquals("john.doe@example.com", userRequest.getEmail());
		assertEquals("12345678901", userRequest.getDocument());
		assertEquals("Password@123", userRequest.getPassword());
	}

	@Test
	void createUserRequestWithEmptyEmail() {
		UserRequest userRequest = new UserRequest("John Doe", "", "12345678901", "Password@123");

		assertNotNull(userRequest);
		assertEquals("John Doe", userRequest.getName());
		assertEquals("", userRequest.getEmail());
		assertEquals("12345678901", userRequest.getDocument());
		assertEquals("Password@123", userRequest.getPassword());
	}

	@Test
	void createUserRequestWithNullDocument() {
		UserRequest userRequest = new UserRequest("John Doe", "john.doe@example.com", null, "Password@123");

		assertNotNull(userRequest);
		assertEquals("John Doe", userRequest.getName());
		assertEquals("john.doe@example.com", userRequest.getEmail());
		assertNull(userRequest.getDocument());
		assertEquals("Password@123", userRequest.getPassword());
	}

	@Test
	void createUserRequestWithEmptyPassword() {
		UserRequest userRequest = new UserRequest("John Doe", "john.doe@example.com", "12345678901", "");

		assertNotNull(userRequest);
		assertEquals("John Doe", userRequest.getName());
		assertEquals("john.doe@example.com", userRequest.getEmail());
		assertEquals("12345678901", userRequest.getDocument());
		assertEquals("", userRequest.getPassword());
	}
}