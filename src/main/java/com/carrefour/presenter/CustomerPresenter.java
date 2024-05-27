package com.carrefour.presenter;

import com.carrefour.dto.CustomerRequestDto;
import com.carrefour.dto.CustomerResponseDto;
import com.carrefour.mapper.CustomerMapper;
import com.carrefour.model.CustomerEntity;
import com.carrefour.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerPresenter {

    private final CustomerRepository customerRepository;

    public CustomerPresenter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = CustomerMapper.toCustomer(customerRequestDto);
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        return CustomerMapper.toCustomerResponseDto(savedCustomerEntity);
    }
}
