package com.fiap.hackathon.user.core.domain;

import com.fiap.challenge.user.core.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

	@Test
	void createUserWithValidData() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");

		assertNotNull(user);
		assertEquals(1L, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("12345678901", user.getDocument());
		assertEquals("Password@123", user.getPassword());
	}

	@Test
	void createUserWithNullId() {
		User user = new User(null, "John Doe", "john.doe@example.com", "12345678901", "Password@123");

		assertNotNull(user);
		assertEquals(null, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("12345678901", user.getDocument());
		assertEquals("Password@123", user.getPassword());
	}

	@Test
	void createUserWithEmptyName() {
		User user = new User(1L, "", "john.doe@example.com", "12345678901", "Password@123");

		assertNotNull(user);
		assertEquals(1L, user.getId());
		assertEquals("", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("12345678901", user.getDocument());
		assertEquals("Password@123", user.getPassword());
	}

	@Test
	void createUserWithEmptyEmail() {
		User user = new User(1L, "John Doe", "", "12345678901", "Password@123");

		assertNotNull(user);
		assertEquals(1L, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("", user.getEmail());
		assertEquals("12345678901", user.getDocument());
		assertEquals("Password@123", user.getPassword());
	}

	@Test
	void createUserWithEmptyDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "", "Password@123");

		assertNotNull(user);
		assertEquals(1L, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("", user.getDocument());
		assertEquals("Password@123", user.getPassword());
	}

	@Test
	void createUserWithEmptyPassword() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "");

		assertNotNull(user);
		assertEquals(1L, user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("12345678901", user.getDocument());
		assertEquals("", user.getPassword());
	}
}