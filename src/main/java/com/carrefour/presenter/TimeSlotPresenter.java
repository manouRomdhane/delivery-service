package com.carrefour.presenter;

import com.carrefour.dto.DeliveryRequestDto;
import com.carrefour.dto.DeliveryResponseDto;
import com.carrefour.dto.TimeSlotRequestDto;
import com.carrefour.dto.TimeSlotResponseDto;
import com.carrefour.mapper.DeliveryMapper;
import com.carrefour.mapper.TimeSlotMapper;
import com.carrefour.model.CustomerEntity;
import com.carrefour.model.DeliveryEntity;
import com.carrefour.model.DeliveryMethodEnum;
import com.carrefour.model.TimeSlotEntity;
import com.carrefour.repository.CustomerRepository;
import com.carrefour.repository.TimeSlotRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TimeSlotPresenter {

    private final TimeSlotRepository timeSlotRepository;
    private final CustomerRepository customerRepository;

    public TimeSlotPresenter(TimeSlotRepository timeSlotRepository, CustomerRepository customerRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.customerRepository = customerRepository;
    }

    public List<TimeSlotEntity> getTimeSlots(DeliveryMethodEnum method) {
        return timeSlotRepository.findByDeliveryMethodEnumAndIsBookedFalse(method);
    }

    public TimeSlotResponseDto createTimeSlot(TimeSlotRequestDto timeSlotRequestDto) {
        TimeSlotEntity timeSlotEntity = TimeSlotMapper.toTimeSlot(timeSlotRequestDto);
        return TimeSlotMapper.toTimeSlotResponseDto(timeSlotRepository.save(timeSlotEntity));
    }

    public DeliveryResponseDto bookTimeSlot(DeliveryRequestDto deliveryRequestDto) {
        Optional<TimeSlotEntity> optionalTimeSlot = timeSlotRepository.findById(deliveryRequestDto.getTimeSlotId());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(deliveryRequestDto.getCustomerId());
        if (optionalTimeSlot.isPresent() && !optionalTimeSlot.get().isBooked() && optionalCustomer.isPresent()) {
            DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                    .description(deliveryRequestDto.getDescription())
                    .customer(optionalCustomer.get())
                    .method(DeliveryMethodEnum.valueOf(deliveryRequestDto.getDeliveryMethod()))
                    .build();
            TimeSlotEntity timeSlotEntity = optionalTimeSlot.get();
            timeSlotEntity.setBooked(true);
            timeSlotEntity.setCustomerEntity(optionalCustomer.get());
            timeSlotEntity.setDeliveryEntity(deliveryEntity);
            deliveryEntity.setTimeSlot(timeSlotEntity);
            return DeliveryMapper.toDeliveryResponseDto(timeSlotRepository.save(timeSlotEntity).getDeliveryEntity());
        }
        return null;
    }
}
