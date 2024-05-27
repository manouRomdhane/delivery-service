package com.carrefour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryResponseDto {

    private Long id;

    private String description;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("is_booked")
    private boolean isBooked;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("time_slot_id")
    private Long timeSlotId;

    @JsonProperty("delivery_method")
    private String deliveryMethod;
}
