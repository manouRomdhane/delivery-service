package com.carrefour.presenter;
import com.carrefour.dto.AddressRequestDto;
import com.carrefour.dto.AddressResponseDto;
import com.carrefour.dto.CustomerRequestDto;
import com.carrefour.dto.CustomerResponseDto;
import com.carrefour.mapper.CustomerMapper;
import com.carrefour.model.CustomerEntity;
import com.carrefour.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerPresenterTest {

    @InjectMocks
    private CustomerPresenter customerPresenter;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCustomer_returnsCustomerResponseDto() {

        AddressRequestDto addressRequestDto = AddressRequestDto.builder().address("123 Main St")
                .city("Springfield").postalCode("12345").country("USA").build();

        CustomerRequestDto customerRequestDto = CustomerRequestDto.builder().firstName("John")
                .lastName("Doe").email("email.com").phoneNumber("1234567890").address(addressRequestDto).build();

        CustomerEntity customerEntity = CustomerMapper.toCustomer(customerRequestDto);
        AddressResponseDto addressResponseDto = AddressResponseDto.builder().address("123 Main St")
                .city("Springfield").postalCode("12345").country("USA").build();
        CustomerResponseDto expectedResponse = CustomerResponseDto.builder().firstName("John")
                .lastName("Doe").email("email.com").phoneNumber("1234567890").address(addressResponseDto).build();

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        CustomerResponseDto actualResponse = customerPresenter.createCustomer(customerRequestDto);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void createCustomer_returnsNull_whenCustomerRequestDtoIsNull() {
        CustomerRequestDto customerRequestDto = null;

        CustomerResponseDto actualResponse = customerPresenter.createCustomer(customerRequestDto);

        assertEquals(null, actualResponse);
    }
}