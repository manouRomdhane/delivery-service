package com.carrefour.controller;

import com.carrefour.dto.TimeSlotRequestDto;
import com.carrefour.dto.TimeSlotResponseDto;
import com.carrefour.presenter.TimeSlotPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TimeSlotControllerTest {

    @InjectMocks
    private TimeSlotController timeSlotController;

    @Mock
    private TimeSlotPresenter timeSlotPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTimeSlot_returnsTimeSlotResponseDto_whenValidRequest() {
        TimeSlotRequestDto requestDto = new TimeSlotRequestDto();
        TimeSlotResponseDto responseDto = new TimeSlotResponseDto();
        when(timeSlotPresenter.createTimeSlot(any(TimeSlotRequestDto.class))).thenReturn(responseDto);

        ResponseEntity<EntityModel<TimeSlotResponseDto>> responseEntity = timeSlotController.createTimeSlot(requestDto);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(responseDto, responseEntity.getBody().getContent());
    }

    @Test
    void createTimeSlot_returnsBadRequest_whenExceptionThrown() {
        TimeSlotRequestDto requestDto = new TimeSlotRequestDto();
        when(timeSlotPresenter.createTimeSlot(any(TimeSlotRequestDto.class))).thenThrow(new RuntimeException());

        ResponseEntity<EntityModel<TimeSlotResponseDto>> responseEntity = timeSlotController.createTimeSlot(requestDto);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}