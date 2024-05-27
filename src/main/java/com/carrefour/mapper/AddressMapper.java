package com.carrefour.mapper;

import com.carrefour.dto.AddressRequestDto;
import com.carrefour.dto.AddressResponseDto;
import com.carrefour.model.AddressEntity;

public class AddressMapper {

    public static AddressEntity toAddress(AddressRequestDto addressRequestDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress(addressRequestDto.getAddress());
        addressEntity.setCity(addressRequestDto.getCity());
        addressEntity.setPostalCode(addressRequestDto.getPostalCode());
        addressEntity.setCountry(addressRequestDto.getCountry());
        return addressEntity;
    }

    public static AddressRequestDto toAddressRequestDto(AddressEntity addressEntity) {
        AddressRequestDto addressRequestDto = AddressRequestDto.builder().build();
        addressRequestDto.setAddress(addressEntity.getAddress());
        addressRequestDto.setCity(addressEntity.getCity());
        addressRequestDto.setPostalCode(addressEntity.getPostalCode());
        addressRequestDto.setCountry(addressEntity.getCountry());
        return addressRequestDto;
    }

    public static AddressResponseDto toAddressResponseDto(AddressEntity addressEntity) {
        AddressResponseDto addressResponseDto = AddressResponseDto.builder().build();
        addressResponseDto.setAddress(addressEntity.getAddress());
        addressResponseDto.setCity(addressEntity.getCity());
        addressResponseDto.setPostalCode(addressEntity.getPostalCode());
        addressResponseDto.setCountry(addressEntity.getCountry());
        return addressResponseDto;
    }
}
