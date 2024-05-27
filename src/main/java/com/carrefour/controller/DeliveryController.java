package com.carrefour.controller;

import com.carrefour.dto.DeliveryRequestDto;
import com.carrefour.dto.DeliveryResponseDto;
import com.carrefour.model.DeliveryMethodEnum;
import com.carrefour.model.TimeSlotEntity;
import com.carrefour.presenter.DeliveryPresenter;
import com.carrefour.presenter.TimeSlotPresenter;
import com.carrefour.view.DeliveryView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/delivery")
@Tag(name = "Delivery Controller", description = "Delivery Controller")
public class DeliveryController implements DeliveryView {

    private final DeliveryPresenter deliveryPresenter;
    private final TimeSlotPresenter timeSlotPresenter;

    public DeliveryController(DeliveryPresenter deliveryPresenter, TimeSlotPresenter timeSlotPresenter) {
        this.deliveryPresenter = deliveryPresenter;
        this.timeSlotPresenter = timeSlotPresenter;
    }

    @GetMapping("/methods")
    @Operation(summary = "Get Delivery Methods", description = "Get the list of delivery methods.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery methods retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<List<EntityModel<DeliveryMethodEnum>>> getDeliveryMethods() {
        List<DeliveryMethodEnum> methods = deliveryPresenter.getDeliveryMethods();
        List<EntityModel<DeliveryMethodEnum>> resources = methods.stream()
                .map(method -> EntityModel.of(method,
                        WebMvcLinkBuilder.linkTo(methodOn(DeliveryController.class).getTimeSlots(method)).withRel("timeslots")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Override
    @GetMapping("/timeslots/{method}")
    @Operation(summary = "Get Time Slots", description = "Get the list of time slots for a specific delivery method.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slots retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<List<EntityModel<TimeSlotEntity>>> getTimeSlots(@PathVariable DeliveryMethodEnum method) {
        List<TimeSlotEntity> timeSlotEntities = timeSlotPresenter.getTimeSlots(method);
        List<EntityModel<TimeSlotEntity>> resources = timeSlotEntities.stream()
                .map(timeSlotEntity -> EntityModel.of(timeSlotEntity,
                        WebMvcLinkBuilder.linkTo(methodOn(DeliveryController.class).getTimeSlots(method)).withRel("timeslots")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/timeslots/book")
    @Operation(summary = "Book Time Slot", description = "Book a time slot for a delivery.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slot booked successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<EntityModel<DeliveryResponseDto>> bookTimeSlot(@RequestBody DeliveryRequestDto deliveryRequestDto) {
        try {
            DeliveryResponseDto deliveryResponseDto = timeSlotPresenter.bookTimeSlot(deliveryRequestDto);
            return ResponseEntity.ok(EntityModel.of(deliveryResponseDto,
                    WebMvcLinkBuilder.linkTo(methodOn(DeliveryController.class).bookTimeSlot(deliveryRequestDto)).withSelfRel()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
