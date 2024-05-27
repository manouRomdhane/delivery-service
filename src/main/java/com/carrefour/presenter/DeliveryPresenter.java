package com.carrefour.presenter;

import com.carrefour.model.DeliveryMethodEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DeliveryPresenter {

    @Cacheable(value = "deliveryMethods")
    public List<DeliveryMethodEnum> getDeliveryMethods() {
        return Arrays.asList(DeliveryMethodEnum.values());
    }

}
