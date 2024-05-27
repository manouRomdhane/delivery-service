package com.carrefour.mapper;


import com.carrefour.dto.CustomerRequestDto;
import com.carrefour.dto.CustomerResponseDto;
import com.carrefour.model.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toCustomer(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null){
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerRequestDto.getFirstName());
        customerEntity.setLastName(customerRequestDto.getLastName());
        customerEntity.setEmail(customerRequestDto.getEmail());
        customerEntity.setPhoneNumber(customerRequestDto.getPhoneNumber());
        if (customerRequestDto.getAddress() != null){
            customerEntity.setAddressEntity(AddressMapper.toAddress(customerRequestDto.getAddress()));
        }
        return customerEntity;
    }

    public static CustomerResponseDto toCustomerResponseDto(CustomerEntity customerEntity) {
        if (customerEntity == null){
            return null;
        }
        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder().build();
        customerResponseDto.setId(customerEntity.getId());
        customerResponseDto.setFirstName(customerEntity.getFirstName());
        customerResponseDto.setLastName(customerEntity.getLastName());
        customerResponseDto.setEmail(customerEntity.getEmail());
        customerResponseDto.setPhoneNumber(customerEntity.getPhoneNumber());
        if (customerEntity.getAddressEntity() != null){
            customerResponseDto.setAddress(AddressMapper.toAddressResponseDto(customerEntity.getAddressEntity()));
        }
        return customerResponseDto;
    }
}
