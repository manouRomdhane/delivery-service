package com.carrefour.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponseDto {

    private String address;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;
}