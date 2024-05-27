package com.carrefour.repository;

import com.carrefour.model.DeliveryMethodEnum;
import com.carrefour.model.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long>{
    List<TimeSlotEntity> findByDeliveryMethodEnumAndIsBookedFalse(DeliveryMethodEnum deliveryMethod);
}

