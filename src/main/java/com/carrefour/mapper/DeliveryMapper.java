package com.carrefour.mapper;

import com.carrefour.dto.DeliveryResponseDto;
import com.carrefour.model.DeliveryEntity;
import com.carrefour.model.TimeSlotEntity;

public class DeliveryMapper {

    public static DeliveryResponseDto toDeliveryResponseDto(DeliveryEntity deliveryEntity) {
        DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();
        deliveryResponseDto.setId(deliveryEntity.getId());
        deliveryResponseDto.setDescription(deliveryEntity.getDescription());
        deliveryResponseDto.setStartTime(deliveryEntity.getTimeSlot().getStartTime());
        deliveryResponseDto.setEndTime(deliveryEntity.getTimeSlot().getEndTime());
        deliveryResponseDto.setBooked(deliveryEntity.getTimeSlot().isBooked());
        deliveryResponseDto.setCustomerId(deliveryEntity.getCustomer().getId());
        deliveryResponseDto.setTimeSlotId(deliveryEntity.getCustomer().getId());
        deliveryResponseDto.setDeliveryMethod(deliveryEntity.getMethod().toString());
        return deliveryResponseDto;
    }


}
