package com.fiap.hackathon.user.common;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fiap.challenge.user.common.JwtTokenUtil;
import com.fiap.challenge.user.core.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtTokenUtilTest {

	private JwtTokenUtil jwtTokenUtil;

	@BeforeEach
	void setUp() {
		jwtTokenUtil = new JwtTokenUtil();
		ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecretKey");
	}

	@Test
	void generateJwtTokenWithValidUser() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");

		String token = jwtTokenUtil.generateJwtToekn(user);

		assertNotNull(token);
	}

	@Test
	void generateJwtTokenWithNullUser() {
		assertThrows(RuntimeException.class, () -> jwtTokenUtil.generateJwtToekn(null));
	}

	@Test
	void generateJwtTokenWithInvalidSecret() {
		ReflectionTestUtils.setField(jwtTokenUtil, "secret", "");

		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");

		assertThrows(IllegalArgumentException.class, () -> jwtTokenUtil.generateJwtToekn(user));
	}
}