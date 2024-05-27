package com.carrefour.mapper;

import com.carrefour.dto.TimeSlotRequestDto;
import com.carrefour.dto.TimeSlotResponseDto;
import com.carrefour.model.DeliveryMethodEnum;
import com.carrefour.model.TimeSlotEntity;

public class TimeSlotMapper {

    public static TimeSlotEntity toTimeSlot(TimeSlotRequestDto timeSlotRequestDto) {
        TimeSlotEntity timeSlot = new TimeSlotEntity();
        timeSlot.setDeliveryMethodEnum(DeliveryMethodEnum.valueOf(timeSlotRequestDto.getDeliveryMethod()));
        timeSlot.setStartTime(timeSlotRequestDto.getStartTime());
        timeSlot.setEndTime(timeSlotRequestDto.getEndTime());
        return timeSlot;
    }

    public static TimeSlotResponseDto toTimeSlotResponseDto(TimeSlotEntity timeSlot) {
        TimeSlotResponseDto timeSlotResponseDto = new TimeSlotResponseDto();
        timeSlotResponseDto.setId(timeSlot.getId());
        timeSlotResponseDto.setDeliveryMethod(timeSlot.getDeliveryMethodEnum().toString());
        timeSlotResponseDto.setStartTime(timeSlot.getStartTime());
        timeSlotResponseDto.setEndTime(timeSlot.getEndTime());
        timeSlotResponseDto.setBooked(timeSlot.isBooked());
        return timeSlotResponseDto;
    }
}
