package com.fiap.hackathon.user.app.adapter.output.persistence.entity;

import com.fiap.challenge.user.app.adapter.output.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserEntityTest {

	@Test
	void createUserEntityWithValidData() {
		UserEntity userEntity = UserEntity.builder()
				.id(1L)
				.name("John Doe")
				.email("john.doe@example.com")
				.document("12345678901")
				.build();

		assertNotNull(userEntity);
		assertEquals(1L, userEntity.getId());
		assertEquals("John Doe", userEntity.getName());
		assertEquals("john.doe@example.com", userEntity.getEmail());
		assertEquals("12345678901", userEntity.getDocument());
	}

	@Test
	void createUserEntityWithNoArgsConstructor() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setName("John Doe");
		userEntity.setEmail("john.doe@example.com");
		userEntity.setDocument("12345678901");

		assertNotNull(userEntity);
		assertEquals(1L, userEntity.getId());
		assertEquals("John Doe", userEntity.getName());
		assertEquals("john.doe@example.com", userEntity.getEmail());
		assertEquals("12345678901", userEntity.getDocument());
	}

	@Test
	void createUserEntityWithAllArgsConstructor() {
		UserEntity userEntity = new UserEntity(1L, "John Doe", "john.doe@example.com", "12345678901");

		assertNotNull(userEntity);
		assertEquals(1L, userEntity.getId());
		assertEquals("John Doe", userEntity.getName());
		assertEquals("john.doe@example.com", userEntity.getEmail());
		assertEquals("12345678901", userEntity.getDocument());
	}

	@Test
	void updateUserEntityWithToBuilder() {
		UserEntity userEntity = UserEntity.builder()
				.id(1L)
				.name("John Doe")
				.email("john.doe@example.com")
				.document("12345678901")
				.build();

		UserEntity updatedUserEntity = userEntity.toBuilder()
				.name("Jane Doe")
				.build();

		assertNotNull(updatedUserEntity);
		assertEquals(1L, updatedUserEntity.getId());
		assertEquals("Jane Doe", updatedUserEntity.getName());
		assertEquals("john.doe@example.com", updatedUserEntity.getEmail());
		assertEquals("12345678901", updatedUserEntity.getDocument());
	}
}