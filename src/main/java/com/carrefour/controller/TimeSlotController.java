package com.carrefour.controller;


import com.carrefour.dto.TimeSlotRequestDto;
import com.carrefour.dto.TimeSlotResponseDto;
import com.carrefour.presenter.TimeSlotPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timeslots")
@Tag(name = "Time Slot Controller", description = "Time Slot Controller")
public class TimeSlotController {
    private final TimeSlotPresenter timeSlotPresenter;

    public TimeSlotController(TimeSlotPresenter timeSlotPresenter) {
        this.timeSlotPresenter = timeSlotPresenter;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Time Slot", description = "Create a new time slot.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slot created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public ResponseEntity<EntityModel<TimeSlotResponseDto>> createTimeSlot(@RequestBody TimeSlotRequestDto timeSlotRequestDto) {
        try {
            TimeSlotResponseDto timeSlotResponseDto = timeSlotPresenter.createTimeSlot(timeSlotRequestDto);
            return ResponseEntity.ok(EntityModel.of(timeSlotResponseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
