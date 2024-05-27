package com.carrefour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressRequestDto {

    private String address;

    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    private String country;
}