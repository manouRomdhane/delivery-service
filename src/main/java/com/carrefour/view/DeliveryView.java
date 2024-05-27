package com.carrefour.view;

import com.carrefour.model.DeliveryMethodEnum;
import com.carrefour.model.TimeSlotEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeliveryView {
    ResponseEntity<List<EntityModel<DeliveryMethodEnum>>> getDeliveryMethods();
    ResponseEntity<List<EntityModel<TimeSlotEntity>>> getTimeSlots(DeliveryMethodEnum method);
}
