package com.carrefour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryRequestDto {

    private String description;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("time_slot_id")
    private Long timeSlotId;

    @JsonProperty("delivery_method")
    private String deliveryMethod;
}
