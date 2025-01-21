package com.fiap.challenge.user.app.adapter.input.web;

import com.fiap.challenge.user.app.adapter.input.web.dto.UserRequest;
import com.fiap.challenge.user.app.adapter.input.web.dto.UserResponse;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserRequestMapper;
import com.fiap.challenge.user.app.adapter.input.web.mapper.UserResponseMapper;
import com.fiap.challenge.user.core.domain.User;
import com.fiap.challenge.user.core.usecases.user.UserCreateUseCase;
import com.fiap.challenge.user.core.usecases.user.UserGetUseCase;
import com.fiap.challenge.user.core.usecases.user.UserListUseCase;
import com.fiap.challenge.user.core.usecases.user.UserRemoveUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserCreateUseCase userCreateUseCase;
    @Mock
    private UserGetUseCase userGetUseCase;
    @Mock
    private UserRemoveUseCase userRemoveUseCase;
    @Mock
    private UserListUseCase userListUseCase;

    private final UserRequestMapper userRequestMapper = mock(UserRequestMapper.class);
    private final UserResponseMapper userResponseMapper = mock(UserResponseMapper.class);

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSave() {
        UserRequest userRequestMock = getUserRequestMock();
        User expected = getUserMock(null);
        UUID id = UUID.randomUUID();
        User expectedWithId = getUserMock(id);
        UserResponse userResponseMock = getUserResponseMock(id);

        when(userRequestMapper.toUser(userRequestMock)).thenReturn(expected);
        when(userCreateUseCase.execute(expected)).thenReturn(expectedWithId);
        when(userResponseMapper.toUserResponse(expectedWithId)).thenReturn(userResponseMock);

        UserResponse userResponse = userController.create(userRequestMock);
        verify(customerRequestMapper, times(1)).toCustomer(any());
        verify(customerCreateUseCase, times(1)).execute(any());
        verify(customerResponseMapper, times(1)).toCustomerResponse(any());
        assertNotNull(customerResponse);
        assertNotNull(customerResponse.getId());
        assertEquals(customerResponse.getId(), id);
    }

    @Test
    void shouldFindById() {
        UUID id = UUID.randomUUID();
        Customer expectedWithId = getCustomerMock(id);
        CustomerResponse customerResponseMock = getCustomerResponseMock(id);

        when(customerGetUseCase.execute(id)).thenReturn(expectedWithId);
        when(customerResponseMapper.toCustomerResponse(expectedWithId)).thenReturn(customerResponseMock);

        CustomerResponse customerResponse = customerController.get(id);
        verify(customerGetUseCase, times(1)).execute(any());
        verify(customerResponseMapper, times(1)).toCustomerResponse(any());
        assertNotNull(customerResponse);
        assertNotNull(customerResponse.getId());
        assertEquals(customerResponse.getId(), id);
    }

    @Test
    void shouldRemove() {
        UUID id = UUID.randomUUID();
        Customer expectedWithId = getCustomerMock(id);
        CustomerResponse customerResponseMock = getCustomerResponseMock(id);

        doNothing().when(customerRemoveUseCase).execute(any());

        customerController.remove(id);
        verify(customerRemoveUseCase, times(1)).execute(any());
    }

    @Test
    void shouldListByDocument() {
        UUID id = UUID.randomUUID();
        List<Customer> customers = List.of(getCustomerMock(id));
        CustomerResponse customerResponseMock = getCustomerResponseMock(id);

        when(customerListUseCase.execute(customerResponseMock.getDocument())).thenReturn(customers);
        when(customerResponseMapper.toCustomer(customers)).thenReturn(List.of(customerResponseMock));

        List<CustomerResponse> customersResponse = customerController.list(customerResponseMock.getDocument());
        verify(customerListUseCase, times(1)).execute(any());
        verify(customerResponseMapper, times(1)).toCustomer(any());
        assertNotNull(customersResponse);
        assertTrue(!customersResponse.isEmpty());
    }

    private CustomerRequest getCustomerRequestMock() {
        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setDocument("01234567890");
        customerRequest.setName("Usuário Teste");
        customerRequest.setPassword("Teste@123");
        customerRequest.setEmail("teste123@teste.com.br");

        return customerRequest;
    }

    private Customer getCustomerMock(UUID id) {
        Customer customer = new Customer();

        if (id != null)
            customer.setId(id);

        customer.setDocument("01234567890");
        customer.setName("Usuário Teste");
        customer.setPassword("Teste@123");
        customer.setEmail("teste123@teste.com.br");

        return customer;
    }

    private CustomerResponse getCustomerResponseMock(UUID id) {
        CustomerResponse customerResponse = new CustomerResponse();

        if (id != null)
            customerResponse.setId(id);
        else
            customerResponse.setId(UUID.randomUUID());

        customerResponse.setDocument("01234567890");
        customerResponse.setName("Usuário Teste");
        customerResponse.setEmail("teste123@teste.com.br");

        return customerResponse;
    }

}
