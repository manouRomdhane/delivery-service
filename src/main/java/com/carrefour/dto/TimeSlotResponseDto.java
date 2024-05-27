package com.carrefour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlotResponseDto {

    private Long id;

    @JsonProperty("delivery_method")
    private String deliveryMethod;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    private boolean isBooked;
}
