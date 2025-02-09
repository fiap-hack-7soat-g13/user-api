package com.fiap.hackathon.user.app.adapter.input.web;

import com.fiap.challenge.user.app.adapter.input.web.UserController;
import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import com.fiap.challenge.user.app.adapter.input.web.dto.UserResponse;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserRequestMapper;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserResponseMapper;
import com.fiap.challenge.user.common.JwtTokenUtil;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.usecases.user.UserCreateUseCase;
import com.fiap.challenge.user.core.usecases.user.UserGetUseCase;
import com.fiap.challenge.user.core.usecases.user.UserListUseCase;
import com.fiap.challenge.user.core.usecases.user.UserRemoveUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

	private UserCreateUseCase userCreateUseCase;
	private UserGetUseCase userGetUseCase;
	private UserRemoveUseCase userRemoveUseCase;
	private UserListUseCase userListUseCase;
	private UserRequestMapper userRequestMapper;
	private UserResponseMapper userResponseMapper;
	private JwtTokenUtil jwtTokenUtil;
	private UserController userController;

	@BeforeEach
	void setUp() {
		userCreateUseCase = Mockito.mock(UserCreateUseCase.class);
		userGetUseCase = Mockito.mock(UserGetUseCase.class);
		userRemoveUseCase = Mockito.mock(UserRemoveUseCase.class);
		userListUseCase = Mockito.mock(UserListUseCase.class);
		userRequestMapper = Mockito.mock(UserRequestMapper.class);
		userResponseMapper = Mockito.mock(UserResponseMapper.class);
		jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
		userController = new UserController(userCreateUseCase, userGetUseCase, userRemoveUseCase, userListUseCase, userRequestMapper, userResponseMapper, jwtTokenUtil);
	}

	@Test
	void createUserWithValidRequest() {
		UserRequest userRequest = new UserRequest("John Doe", "john.doe@example.com", "12345678901", "Password@123");
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", "12345678901");

		when(userRequestMapper.toUser(userRequest)).thenReturn(user);
		when(userCreateUseCase.execute(user)).thenReturn(user);
		when(userResponseMapper.toUserResponse(user)).thenReturn(userResponse);

		UserResponse result = userController.create(userRequest);

		assertNotNull(result);
		assertEquals(userResponse, result);
	}

	@Test
	void getUserWithValidId() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", "12345678901");

		when(userGetUseCase.execute(1L)).thenReturn(user);
		when(userResponseMapper.toUserResponse(user)).thenReturn(userResponse);

		UserResponse result = userController.get(1L);

		assertNotNull(result);
		assertEquals(userResponse, result);
	}

	@Test
	void removeUserWithValidId() {
		doNothing().when(userRemoveUseCase).execute(1L);

		userController.remove(1L);

		verify(userRemoveUseCase).execute(1L);
	}

	@Test
	void listUsersWithDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", "12345678901");

		when(userListUseCase.execute("12345678901")).thenReturn(Collections.singletonList(user));
		when(userResponseMapper.toUser(Collections.singletonList(user))).thenReturn(Collections.singletonList(userResponse));

		List<UserResponse> result = userController.list("12345678901");

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(userResponse, result.get(0));
	}

	@Test
	void listUsersWithoutDocument() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		UserResponse userResponse = new UserResponse(1L, "John Doe", "john.doe@example.com", "12345678901");

		when(userListUseCase.execute(null)).thenReturn(Collections.singletonList(user));
		when(userResponseMapper.toUser(Collections.singletonList(user))).thenReturn(Collections.singletonList(userResponse));

		List<UserResponse> result = userController.list(null);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(userResponse, result.get(0));
	}

	@Test
	void getAuthenticationWithValidId() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");
		String token = "jwtToken";

		when(userGetUseCase.execute(1L)).thenReturn(user);
		when(jwtTokenUtil.generateJwtToekn(user)).thenReturn(token);

		String result = userController.getAuthentication(1L);

		assertNotNull(result);
		assertEquals("Bearer " + token, result);
	}

	@Test
	void getAuthenticationWithException() {
		User user = new User(1L, "John Doe", "john.doe@example.com", "12345678901", "Password@123");

		when(userGetUseCase.execute(1L)).thenReturn(user);
		when(jwtTokenUtil.generateJwtToekn(user)).thenThrow(new RuntimeException("Erro ao gerar token"));

		assertThrows(RuntimeException.class, () -> userController.getAuthentication(1L));
	}
}