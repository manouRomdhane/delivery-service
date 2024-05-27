package com.carrefour.controller;

import com.carrefour.dto.CustomerRequestDto;
import com.carrefour.dto.CustomerResponseDto;
import com.carrefour.presenter.CustomerPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer Controller", description = "Customer Controller")
public class CustomerController {

    private final CustomerPresenter presenter;

    @Autowired
    public CustomerController(CustomerPresenter presenter) {
        this.presenter = presenter;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Customer", description = "Create a new customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<EntityModel<CustomerResponseDto>> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        try {
            CustomerResponseDto createdCustomer = presenter.createCustomer(customerRequestDto);
            return ResponseEntity.ok(EntityModel.of(createdCustomer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
